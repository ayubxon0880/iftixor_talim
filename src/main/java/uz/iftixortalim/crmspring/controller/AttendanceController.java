package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.attendance.*;
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
    public ResponseEntity<List<AttendanceParent>> readByGroupId(@RequestParam Integer month,
                                                                @RequestParam Optional<Integer> day,
                                                                @PathVariable Long groupId){
        return attendanceService.readByGroupId(month,day,groupId);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceBig>> read(@RequestParam Integer year){
        return attendanceService.read(year);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody AttendanceForSave attendances){
        return attendanceService.save(attendances);
    }


    @GetMapping("/pagination")
    public ResponseEntity<List<AttendanceDTO>> readAllByPagination(@RequestParam Optional<Integer> page,
                                                                   @RequestParam Optional<Integer> year,
                                                                   @RequestParam Optional<Integer> month,
                                                                   @RequestParam Optional<Integer> studentName){
        return attendanceService.readAllByPagination(page,year,month,studentName);
    }


}
