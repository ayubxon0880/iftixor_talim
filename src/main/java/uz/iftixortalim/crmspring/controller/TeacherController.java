package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.teacher.TeacherDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
@Validated
public class TeacherController {
    private final TeacherService teacherService;
    @PostMapping
    @Validated(value = OnCreate.class)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody TeacherDTO teacherDTO){
        return teacherService.create(teacherDTO);
    }

    @GetMapping
    public ResponseEntity<TeacherDTO> read(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return teacherService.getById(user.getId());
    }

    @GetMapping("/get-me")
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER')")
    public ResponseEntity<TeacherDTO> getMe(){
        return teacherService.getMe();
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherDTO>> readAll(){
        return teacherService.getAll();
    }

    @PutMapping
    @Validated(value = OnUpdate.class)
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody TeacherDTO teacherDTO){
        return teacherService.update(teacherDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return teacherService.delete(id);
    }
}


