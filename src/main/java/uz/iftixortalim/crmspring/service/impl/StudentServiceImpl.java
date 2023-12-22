package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.dto.student.StudentDTOForSave;
import uz.iftixortalim.crmspring.dto.student.StudentSmallDto;
import uz.iftixortalim.crmspring.exception.AlreadyExists;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.mapper.StudentMapper;
import uz.iftixortalim.crmspring.model.Group;
import uz.iftixortalim.crmspring.model.Student;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.repository.GroupRepository;
import uz.iftixortalim.crmspring.repository.RoleRepository;
import uz.iftixortalim.crmspring.repository.StudentRepository;
import uz.iftixortalim.crmspring.repository.UserRepository;
import uz.iftixortalim.crmspring.service.StudentService;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;

    @Override
    public ResponseEntity<ApiResponse> create(StudentDTOForSave studentDTO) {
        if (userRepository.existsByUsername(studentDTO.getUsername()))
            throw new AlreadyExists("Student Username already exists");

        studentDTO.setStatus("IN_PROGRESS");
        Student student = studentMapper.toEntity(studentDTO);

        User user = userRepository.save(User
                .builder()
                .createdAt(LocalDate.now())
                .role(roleRepository.findByName("ROLE_STUDENT").orElseThrow(() -> new NotFoundException("Role not found in")))
                .username(studentDTO.getUsername())
                .password(passwordEncoder.encode(studentDTO.getPassword()))
                .build());
        User save = userRepository.save(user);
        student.setId(save.getId());
        studentRepository.save(student);
        return ResponseEntity.ok(ApiResponse.builder().message("Student created").status(201).success(true).build());
    }

    @Override
    public ResponseEntity<StudentDTO> getById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));
        return ResponseEntity.ok(studentMapper.toDto(student));
    }


    @Override
    public ResponseEntity<StudentDTO> update(StudentDTO studentDTO) {
        String username = studentDTO.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new AlreadyExists("Student Username already exists");
        }
        studentRepository.findById(studentDTO.getId()).orElseThrow(() -> new NotFoundException("Student not found"));
        User user = userRepository.findById(studentDTO.getId()).orElseThrow(() -> new NotFoundException("Student not found"));
        user.setUsername(username);
        userRepository.save(user);
        Student save = studentRepository.save(studentMapper.toEntity(studentDTO));
        return ResponseEntity.ok(studentMapper.toDto(save));
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long id) {
        try {
            if (!studentRepository.existsById(id)) {
                throw new NotFoundException("Student not found");
            }
            studentRepository.deleteById(id);
            return ResponseEntity.ok(ApiResponse.builder().success(true).status(200).message("Student deleted").build());
        } catch (Exception e) {
            return ResponseEntity.status(300).body(ApiResponse.builder().success(false).status(300).message("Something is wrong").build());
        }
    }

    @Override
    public ResponseEntity<List<StudentDTO>> getAll(Long groupId, Optional<String> search) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Gurux topilmadi"));
        List<StudentDTO> students = search.map(s -> group.getStudents().stream().filter(it -> it.getFullName().contains(s)).map(studentMapper::toDto).toList()).orElseGet(() -> group.getStudents().stream().map(studentMapper::toDto).toList());
        return ResponseEntity.ok(students);
    }

    @Override
    public ResponseEntity<List<StudentDTOForSave>> readAllByPagination(Optional<Integer> page, Optional<String> studentName) {
        List<StudentDTOForSave> byPagination = studentRepository
                .findAllByFullNameLike("%" + studentName.orElse("") + "%")
                .stream()
                .map(studentMapper::toSmallDto)
                .toList();
//        Pageable pageable = PageRequest.of(page.orElse(1), 3, Sort.by("fullName").descending());
//        byPagination = studentName.map(s ->
//                studentRepository
//                        .findAllByFullNameLike("%" + s + "%", pageable)
//                        .stream()
//                        .map(studentMapper::toSmallDto)
//                        .toList()
//        ).orElseGet(
//                () -> studentRepository
//                        .findAll(pageable)
//                        .stream()
//                        .map(studentMapper::toSmallDto)
//                        .toList()
//        );

        return ResponseEntity.ok(byPagination);
    }

    @Override
    public ResponseEntity<ApiResponse> addGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("O'quvchi topilmadi"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Guruh topilmadi"));
        Set<Group> groups = student.getGroups();
        groups.add(group);
        student.setGroups(groups);
        studentRepository.save(student);

        return ResponseEntity.ok(ApiResponse.builder().success(true).status(202).message("O'quvchi guruhga qo'shildi").build());
    }

    @Override
    public ResponseEntity<ApiResponse> removeFromGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("O'quvchi topilmadi"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Guruh topilmadi"));
        Set<Group> groups = student.getGroups();
        groups.remove(group);
        student.setGroups(groups);
        studentRepository.save(student);

        return ResponseEntity.ok(ApiResponse.builder().success(true).status(200).message("O'quvchi guruhdan chiqarildi").build());
    }

    @Override
    public ResponseEntity<List<StudentSmallDto>> readAllByName(String studentName) {
        List<StudentSmallDto> students = studentRepository.findAllByFullNameLike("%" + studentName + "%").stream().map(student -> new StudentSmallDto(student.getId(),student.getFullName())).toList();
        return ResponseEntity.ok(students);
    }
}