package uz.iftixortalim.crmspring.service;


import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.teacher.TeacherDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;

public interface TeacherService {
    ResponseEntity<ApiResponse> create(TeacherDTO teacherDTO);

    ResponseEntity<TeacherDTO> getById(Long id);

    ResponseEntity<ApiResponse> update(TeacherDTO teacherDTO);

    ResponseEntity<ApiResponse> delete(Long id);

    ResponseEntity<List<TeacherDTO>> getAll();

    ResponseEntity<TeacherDTO> getMe();
}
