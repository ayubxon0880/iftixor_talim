package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceDTO;
import uz.iftixortalim.crmspring.dto.student.*;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    ResponseEntity<ApiResponse> create(StudentDTOForSave studentDTO);

    ResponseEntity<StudentDTO> getById(Long id);

    ResponseEntity<StudentDTO> update(StudentDTO studentDTO);

    ResponseEntity<ApiResponse> delete(Long id);

    ResponseEntity<List<StudentDTO>> getAll(Long groupId, Optional<String> search);

    ResponseEntity<List<StudentDTOForSave>> readAllByPagination(Optional<Integer> page, Optional<String> studentName);

    ResponseEntity<ApiResponse> addGroup(Long studentId, Long groupId);

    ResponseEntity<ApiResponse> removeFromGroup(Long studentId, Long groupId);

    ResponseEntity<List<StudentSmallDto>> readAllByName(String studentName);

    ResponseEntity<StudentDTOForAuth> getMe(Long id);

    ResponseEntity<Boolean> existByName(String name);
}
