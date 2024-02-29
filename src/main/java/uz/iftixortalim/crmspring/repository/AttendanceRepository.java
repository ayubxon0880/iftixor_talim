package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Attendance;
import uz.iftixortalim.crmspring.model.Group;
import uz.iftixortalim.crmspring.repository.service.AttendanceRepositoryService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long>, AttendanceRepositoryService {
    List<Attendance> findAttendanceByStudentIdAndAttendanceDateBetweenOrderByAttendanceDateDesc(Long student_id, LocalDate attendanceDate, LocalDate attendanceDate2);
    List<Attendance> findAttendanceByGroupIdAndAttendanceDateBetweenOrderByAttendanceDateDesc(Long group_id, LocalDate first, LocalDate last);
    boolean existsAttendanceByAttendanceDateAndGroupId(LocalDate attendanceDate, Long group_id);
    List<Attendance> findAttendanceByStudentIdAndGroupInAndAttendanceDateBetweenOrderByAttendanceDateDesc(Long studentId, List<Group> group, LocalDate first, LocalDate last);
}

