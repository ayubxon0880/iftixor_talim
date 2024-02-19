package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.attendance.AttendanceDTO;
import uz.iftixortalim.crmspring.dto.student.*;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;
import uz.iftixortalim.crmspring.model.User;
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
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody StudentDTOForSave studentDTO) {
        String username = studentDTO.getFullName().toLowerCase().replace("'","").replace(" ","");
        studentDTO.setPassword(username);
        studentDTO.setUsername(username);
        studentDTO.setPhone("1");
        return studentService.create(studentDTO);
    }

    @PostMapping("/add-group")
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> addToGroup(@RequestParam Long studentId,@RequestParam Long groupId) {
        return studentService.addGroup(studentId,groupId);
    }

    @DeleteMapping("/remove-group")
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> removeFromGroup(@RequestParam Long studentId,@RequestParam Long groupId) {
        return studentService.removeFromGroup(studentId,groupId);
    }


    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Boolean> existByName(@PathVariable String name) {
        return studentService.existByName(name);
    }


    @GetMapping("/get-me")
    @PreAuthorize(value = "hasAnyRole('ROLE_STUDENT')")
    public ResponseEntity<StudentDTOForAuth> getMe() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null){
            return ResponseEntity.ok(new StudentDTOForAuth());
        }
        return studentService.getMe(user.getId());
    }


    @GetMapping("/group/{groupId}")
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<StudentDTO>> getAll(@PathVariable Long groupId, @RequestParam Optional<String> search) {
        return studentService.getAll(groupId, search);
    }

    @PutMapping
    @Validated(value = OnUpdate.class)
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.update(studentDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return studentService.delete(id);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<StudentDTOForSave>> readAllByPagination(@RequestParam Optional<Integer> page,
                                                                   @RequestParam Optional<String> studentName){
        return studentService.readAllByPagination(page,studentName);
    }

    @GetMapping("/get-by-name")
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<StudentSmallDto>> readAllByName(@RequestParam String studentName){
        return studentService.readAllByName(studentName);
    }
}





















