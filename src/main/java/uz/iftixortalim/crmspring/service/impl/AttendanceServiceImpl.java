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

    private final String ZONE = "Asia/Tokyo";

    @Override
    public ResponseEntity<List<AttendanceDTO>> readByGroupId(Optional<Integer> year, Optional<Integer> month, Long groupId) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<AttendanceBig>> readByStudentId(Long studentId) {
        LocalDate currentDate = LocalDate.now(ZoneId.of(ZONE)); // JST = Asian/Tokyo
        int year = currentDate.getYear();
        LocalDate first = LocalDate.of(year, Month.JANUARY, 1);
        LocalDate last = LocalDate.of(year, Month.DECEMBER, 31);

        List<Attendance> attendanceList = attendanceRepository.findAttendanceByStudentIdAndAttendanceDateBetweenOrderByAttendanceDateDesc(studentId,first,last);
        AttendanceType attendanceType = new AttendanceType();
        List<AttendanceBig> list = attendanceType.add(attendanceList);
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<List<AttendanceBig>> read() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return readByStudentId(user.getId());
    }

    @Override
    public ResponseEntity<ApiResponse> save(AttendanceForSave attendances) {
        Group group = groupRepository.findById(attendances.getGroupId()).orElseThrow(() -> new NotFoundException("Gurux topilmadi"));
        Map<Long, String> map = attendances.getAttendances();
        List<Student> students = studentRepository.findAllById(map.keySet());
        List<Attendance> attendanceList = new ArrayList<>();
        for (Student student : students) {
            attendanceList.add(new Attendance(null, LocalDate.now(), student, group, map.get(student.getId())));
        }
        attendanceRepository.saveAll(attendanceList);
        return ResponseEntity.status(201).body(ApiResponse.builder().status(201).message("Davomat qo'shildi").build());
    }
}
