package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.TeacherDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.exception.AlreadyExists;
import uz.iftixortalim.crmspring.mapper.TeacherMapper;
import uz.iftixortalim.crmspring.model.Teacher;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.repository.RoleRepository;
import uz.iftixortalim.crmspring.repository.TeacherRepository;
import uz.iftixortalim.crmspring.repository.UserRepository;
import uz.iftixortalim.crmspring.service.TeacherService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ApiResponse> create(TeacherDTO teacherDTO) {
        if (userRepository.existsByUsername(teacherDTO.getUsername())) {
            throw new AlreadyExists("Username already exists");
        }
        User user = User
                .builder()
                .createdAt(LocalDate.now())
                .username(teacherDTO.getUsername())
                .password(passwordEncoder.encode(teacherDTO.getPassword()))
                .role(roleRepository.findByName("ROLE_TEACHER").orElseThrow(() -> new NotFoundException("Role not found")))
                .build();
        User save = userRepository.save(user);
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        teacher.setId(save.getId());
        teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().status(201).success(true).message("Teacher created").build());
    }

    @Override
    public ResponseEntity<TeacherDTO> getById(Long id) {
        TeacherDTO teacherDTO = teacherMapper.toDto(teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("Teacher not found")));
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Teacher not found"));
        teacherDTO.setUsername(user.getUsername());
        return ResponseEntity.ok(teacherDTO);
    }

    @Override
    public ResponseEntity<ApiResponse> update(TeacherDTO teacherDTO) {
        teacherRepository.findById(teacherDTO.getId()).orElseThrow(() -> new NotFoundException("Teacher not found"));
        User user = userRepository.findById(teacherDTO.getId()).orElseThrow(() -> new NotFoundException("Teacher not found"));
        user.setUsername(teacherDTO.getUsername());
        Teacher entity = teacherMapper.toEntity(teacherDTO);
        entity.setId(teacherDTO.getId());
        userRepository.save(user);
        teacherRepository.save(entity);
        return ResponseEntity.ok(ApiResponse
                .builder()
                .status(200)
                .message("Teacher updated")
                .success(true)
                .build());
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long id) {
        if (userRepository.existsById(id)) {
            throw new NotFoundException("Teacher not found");
        }
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok(ApiResponse.builder().status(200).success(true).message("Teacher deleted").build());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ApiResponse.builder().success(false).message("Something wrong").build());
        }
    }

    @Override
    public ResponseEntity<List<TeacherDTO>> getAll() {
        return ResponseEntity.ok(teacherRepository.findAll().stream().map(teacherMapper::toDto).toList());
    }
}
