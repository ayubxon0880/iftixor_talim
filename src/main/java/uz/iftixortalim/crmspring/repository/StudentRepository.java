package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Student;
import uz.iftixortalim.crmspring.repository.service.StudentRepositoryService;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>, StudentRepositoryService {
    List<Student> findAllByFullNameLike(String fullName);
}
