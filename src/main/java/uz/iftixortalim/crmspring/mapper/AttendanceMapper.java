package uz.iftixortalim.crmspring.mapper;

import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceDTO;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceForSave;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceSmall;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceSmallDTO;
import uz.iftixortalim.crmspring.model.Attendance;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AttendanceMapper {

    public AttendanceDTO toDto(Attendance attendance){
        return attendance == null ? null : new AttendanceDTO(
                attendance.getId(),
                attendance.getAttendanceDate(),
                Map.of(attendance.getStudent() != null ? attendance.getStudent().getId() : 0L,attendance.getStudent() != null ? attendance.getStudent().getFullName() : "None"),
                Map.of(attendance.getGroup() != null ? attendance.getGroup().getId() : 0L,attendance.getGroup() != null ? attendance.getGroup().getDirection() : "None"),
                attendance.getAttendance()
        );
    }

    public AttendanceSmallDTO toSmallDto(Attendance attendance){
        return attendance == null ? null : new AttendanceSmallDTO(
                attendance.getId(),
                attendance.getStudent() != null ? attendance.getStudent().getFullName() : "Deleted student",
                attendance.getAttendance()
        );
    }

    public AttendanceSmall toSmall(Attendance attendance){
        return attendance == null ? null : new AttendanceSmall(
                attendance.getAttendanceDate(),
                attendance.getAttendance()
        );
    }
}
