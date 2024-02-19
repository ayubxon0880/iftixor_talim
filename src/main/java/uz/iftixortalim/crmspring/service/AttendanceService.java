package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.attendance.*;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {
    ResponseEntity<List<AttendanceParent>> readByGroupId(Integer month, Integer day,Long groupId);
    ResponseEntity<List<AttendanceBig>> readByStudentId(Long studentId,Long groupId,Integer year);
    ResponseEntity<List<AttendanceBig>> read(Integer year,Long groupId);
    ResponseEntity<ApiResponse> save(AttendanceForSave attendances);
    ResponseEntity<List<AttendanceDTO>> readAllByPagination(Optional<Integer> page, Optional<Integer> year, Optional<Integer> month, Optional<Integer> studentName);
    ResponseEntity<ApiResponse> update(Long studentId);
}
