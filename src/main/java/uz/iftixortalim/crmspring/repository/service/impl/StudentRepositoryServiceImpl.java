package uz.iftixortalim.crmspring.repository.service.impl;

import uz.iftixortalim.crmspring.model.Student;
import uz.iftixortalim.crmspring.repository.service.StudentRepositoryService;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryServiceImpl implements StudentRepositoryService {
    @Override
    public List<Student> findByPagination(Optional<Integer> page, Optional<Integer> studentName) {
        return null;
    }
}
