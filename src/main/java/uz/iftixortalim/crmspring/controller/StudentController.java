package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.dto.student.StudentDTOForSave;
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
}
