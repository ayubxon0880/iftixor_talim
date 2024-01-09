package uz.iftixortalim.crmspring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Group;
import uz.iftixortalim.crmspring.model.Student;
import uz.iftixortalim.crmspring.repository.service.StudentRepositoryService;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>, StudentRepositoryService {
    List<Student> findAllByFullNameLike(String fullName, Pageable pageable);
    List<Student> findAllByFullNameLike(String fullName);

//    @Query("SELECT uz.iftixortalim.crmspring.dto.student.StudentDTOForAuth(s.id, s.fullName, s.phone, s.status.name) from Student s WHERE s.id = ?1")
//    Optional<StudentDTOForAuth> findByIdHandle(Long id);

    List<Student> findByGroupsContains(Group group);
}
