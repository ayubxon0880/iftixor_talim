package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Teacher;
import uz.iftixortalim.crmspring.model.User;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
