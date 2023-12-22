package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceDTO;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.dto.student.StudentDTOForSave;
import uz.iftixortalim.crmspring.dto.student.StudentSmallDto;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;
import uz.iftixortalim.crmspring.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Validated
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @Validated(value = OnCreate.class)
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody StudentDTOForSave studentDTO) {
        return studentService.create(studentDTO);
    }

    @PostMapping("/add-group")
    public ResponseEntity<ApiResponse> addToGroup(@RequestParam Long studentId,@RequestParam Long groupId) {
        return studentService.addGroup(studentId,groupId);
    }

    @DeleteMapping("/remove-group")
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> removeFromGroup(@RequestParam Long studentId,@RequestParam Long groupId) {
        return studentService.removeFromGroup(studentId,groupId);
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        return studentService.getById(id);
    }


    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<StudentDTO>> getAll(@PathVariable Long groupId, @RequestParam Optional<String> search) {
        return studentService.getAll(groupId, search);
    }

    @PutMapping
    @Validated(value = OnUpdate.class)
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.update(studentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return studentService.delete(id);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<StudentDTOForSave>> readAllByPagination(@RequestParam Optional<Integer> page,
                                                                   @RequestParam Optional<String> studentName){
        return studentService.readAllByPagination(page,studentName);
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<List<StudentSmallDto>> readAllByName(@RequestParam String studentName){
        return studentService.readAllByName(studentName);
    }
}