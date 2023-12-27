package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.attendance.*;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {
    ResponseEntity<List<AttendanceDTO>> readByGroupId(Optional<Integer> year, Optional<Integer> month, Long groupId);
    ResponseEntity<List<AttendanceBig>> readByStudentId(Long studentId,Integer year);
    ResponseEntity<List<AttendanceBig>> read(Integer year);
    ResponseEntity<ApiResponse> save(AttendanceForSave attendances);
    ResponseEntity<List<AttendanceDTO>> readAllByPagination(Optional<Integer> page, Optional<Integer> year, Optional<Integer> month, Optional<Integer> studentName);
}
