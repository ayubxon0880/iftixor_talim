package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.attendance.*;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.mapper.AttendanceMapper;
import uz.iftixortalim.crmspring.model.Attendance;
import uz.iftixortalim.crmspring.model.Group;
import uz.iftixortalim.crmspring.model.Student;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.repository.AttendanceRepository;
import uz.iftixortalim.crmspring.repository.GroupRepository;
import uz.iftixortalim.crmspring.repository.StudentRepository;
import uz.iftixortalim.crmspring.service.AttendanceService;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final AttendanceMapper attendanceMapper;
    private final String ZONE = "Asia/Tokyo";


    @Override
    public ResponseEntity<List<AttendanceParent>> readByGroupId(Integer month, Integer day, Long groupId) {
        LocalDate date = LocalDate.now(ZoneId.of(ZONE));
        int year = date.getYear();
        Month _month = Month.of(month);

        int length = _month.length(isLeapYear(year));
        if (length < day || day <= 0) {
            throw new NotFoundException("kun xato kiritilgan");
        }
        LocalDate first = LocalDate.of(year, _month, day);
        LocalDate last = LocalDate.of(year, _month, day);
        List<Attendance> attendanceDateDesc = attendanceRepository.findAttendanceByGroupIdAndAttendanceDateBetweenOrderByAttendanceDateDesc(groupId, first, last);

        List<AttendanceParent> parents = new ArrayList<>();
        Map<LocalDate, List<AttendanceSmallDTO>> map = new HashMap<>();

        for (Attendance attendance : attendanceDateDesc) {
            if (map.get(attendance.getAttendanceDate()) == null) {
                map.put(attendance.getAttendanceDate(), new ArrayList<>(List.of(attendanceMapper.toSmallDto(attendance))));
            } else {
                List<AttendanceSmallDTO> attendanceSmallDTOS = map.get(attendance.getAttendanceDate());
                attendanceSmallDTOS.add(attendanceMapper.toSmallDto(attendance));
                map.put(attendance.getAttendanceDate(), attendanceSmallDTOS);
            }
        }

        map.forEach((localDate, attendances) -> parents.add(
                        AttendanceParent
                                .builder()
                                .date(localDate)
                                .attendanceSmallDTO(attendances)
                                .build()
                )
        );

        return ResponseEntity.ok(parents);
    }

    @Override
    public ResponseEntity<List<AttendanceBig>> readByStudentId(Long studentId, Long groupId, Integer year) {
        LocalDate first = LocalDate.of(year, Month.JANUARY, 1);
        LocalDate last = LocalDate.of(year, Month.DECEMBER, Month.DECEMBER.length(isLeapYear(year)));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Group not found"));
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByStudentIdAndGroupInAndAttendanceDateBetweenOrderByAttendanceDateDesc(studentId, List.of(group), first, last);
//        List<Attendance> attendanceList = attendanceRepository.findAttendanceByStudentIdAndAttendanceDateBetweenOrderByAttendanceDateDesc(studentId,first, last);

        List<AttendanceBig> attendanceBigs = new ArrayList<>();
        Map<String, List<AttendanceSmall>> attendanceMap = new HashMap<>();

        for (Attendance attendance : attendanceList) {
            String month = attendance.getAttendanceDate().getMonth().name();
            if (attendanceMap.get(month) == null) {
                attendanceMap.put(month, new ArrayList<>(List.of(attendanceMapper.toSmall(attendance))));
            } else {
                List<AttendanceSmall> attendanceSmall = attendanceMap.get(month);
                attendanceSmall.add(attendanceMapper.toSmall(attendance));
                attendanceMap.put(month, attendanceSmall);
            }

        }

        attendanceMap.forEach((month, attendanceSmall) -> attendanceBigs.add(
                        AttendanceBig
                                .builder()
                                .childAttendance(attendanceSmall)
                                .month(month)
                                .build()
                )
        );

        return ResponseEntity.ok(attendanceBigs);
    }

    @Override
    public ResponseEntity<List<AttendanceBig>> read(Integer year, Long groupId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return readByStudentId(user.getId(), groupId, year);
    }

    @Override
    public ResponseEntity<ApiResponse> save(AttendanceForSave attendances) {
        if (attendanceRepository.existsAttendanceByAttendanceDateAndGroupId(
                LocalDate.now(ZoneId.of(ZONE)),
                attendances.getGroupId()
        )) {
            return ResponseEntity.status(403).body(ApiResponse.builder().status(403).message("Davomat qilingan").success(false).build());
        }

        Group group = groupRepository.findById(attendances.getGroupId()).orElseThrow(() -> new NotFoundException("Gurux topilmadi"));
        List<SmallAttendance> smallAttendances = attendances.getAttendances();
        Map<Long, String> map = new HashMap<>();
        for (SmallAttendance attendance : smallAttendances) {
            map.put(attendance.getId(), attendance.getStatus());
        }
        List<Student> students = studentRepository.findAllById(map.keySet());
        List<Attendance> attendanceList = new ArrayList<>();
        for (Student student : students) {
            attendanceList.add(new Attendance(null, LocalDate.now(), student, group, map.get(student.getId())));
        }
        attendanceRepository.saveAll(attendanceList);
        return ResponseEntity.status(201).body(ApiResponse.builder().status(201).message("Davomat qo'shildi").success(true).build());
    }

    @Override
    public ResponseEntity<List<AttendanceDTO>> readAllByPagination(Optional<Integer> page, Optional<Integer> year, Optional<Integer> month, Optional<Integer> studentName) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> update(Long attId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        if (!(user.getRole().getName().equals("ROLE_TEACHER") || user.getRole().getName().equals("ROLE_ADMIN") || user.getRole().getName().equals("ROLE_SUPER_ADMIN"))) {
            return ResponseEntity.ok(ApiResponse.builder().build());
        }

        Attendance attendance = attendanceRepository.findById(attId).orElseThrow(() -> new NotFoundException("Davomat topilmadi"));
        attendance.setAttendance("Qatnashdi");
        attendanceRepository.save(attendance);
        return ResponseEntity.ok(ApiResponse.builder().success(true).status(200).message("Davomat almashtirildi").build());
    }


    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
