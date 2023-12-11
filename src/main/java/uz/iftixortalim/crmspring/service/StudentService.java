package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    ResponseEntity<ApiResponse> create(StudentDTO studentDTO);

    ResponseEntity<StudentDTO> getById(Long id);

    ResponseEntity<StudentDTO> update(StudentDTO studentDTO);

    ResponseEntity<ApiResponse> delete(Long id);

    ResponseEntity<List<StudentDTO>> getAll(Long groupId, Optional<String> search);
}
