package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.attendance.*;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    ResponseEntity<List<AttendanceDTO>> readByGroupId(Optional<Integer> year, Optional<Integer> month, Long groupId);
    ResponseEntity<List<AttendanceBig>> readByStudentId(Long studentId);
    ResponseEntity<List<AttendanceBig>> read();
    ResponseEntity<ApiResponse> save(AttendanceForSave attendances);
}
