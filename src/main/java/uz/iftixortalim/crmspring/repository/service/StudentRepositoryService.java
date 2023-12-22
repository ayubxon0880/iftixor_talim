package uz.iftixortalim.crmspring.repository.service;

import uz.iftixortalim.crmspring.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepositoryService {
    List<Student> findByPagination(Optional<Integer> page, Optional<Integer> studentName);
}
