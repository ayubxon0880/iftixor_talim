package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Attendance;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    List<Attendance> findByAttendanceDateBetweenAndGroupIdOrderByAttendanceDateDesc(LocalDate first, LocalDate last, Long group_id);
    List<Attendance> findAttendanceByStudentId(Long studentId);
    List<Attendance> findAttendanceByStudentIdAndAttendanceDateBetweenOrderByAttendanceDateDesc(Long student_id, LocalDate first, LocalDate last);
}
