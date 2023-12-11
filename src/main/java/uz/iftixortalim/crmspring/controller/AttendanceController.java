package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceBig;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceDTO;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceForSave;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;
import uz.iftixortalim.crmspring.model.Attendance;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.service.AttendanceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
@Validated
public class AttendanceController {
    private final AttendanceService attendanceService;


    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<AttendanceDTO>> readByGroupId(@RequestParam Optional<Integer> year,
                                                                 @RequestParam Optional<Integer> month,
                                                                 @PathVariable Long groupId){
        return attendanceService.readByGroupId(year,month,groupId);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceBig>> read(){
        return attendanceService.read();
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody AttendanceForSave attendances){
        return attendanceService.save(attendances);
    }


}
