package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Attendance;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    List<Attendance> findAttendanceByStudentIdAndAttendanceDateBetweenOrderByAttendanceDateDesc(Long student_id, LocalDate first, LocalDate last);
    boolean existsAttendanceByAttendanceDateAndGroupId(LocalDate attendanceDate, Long group_id);
}
