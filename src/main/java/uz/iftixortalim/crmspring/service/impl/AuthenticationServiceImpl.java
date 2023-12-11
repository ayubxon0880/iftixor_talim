package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.config.JwtService;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;
import uz.iftixortalim.crmspring.dto.TeacherDTO;
import uz.iftixortalim.crmspring.dto.UserDTO;
import uz.iftixortalim.crmspring.dto.response.AuthenticationRequest;
import uz.iftixortalim.crmspring.dto.response.AuthenticationResponse;
import uz.iftixortalim.crmspring.dto.response.RegisterRequest;
import uz.iftixortalim.crmspring.dto.student.StudentDTOForAuth;
import uz.iftixortalim.crmspring.exception.AuthenticationException;
import uz.iftixortalim.crmspring.exception.Messages;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.exception.AlreadyExists;
import uz.iftixortalim.crmspring.mapper.StudentMapper;
import uz.iftixortalim.crmspring.mapper.TeacherMapper;
import uz.iftixortalim.crmspring.mapper.UserMapper;
import uz.iftixortalim.crmspring.model.Role;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.repository.RoleRepository;
import uz.iftixortalim.crmspring.repository.StudentRepository;
import uz.iftixortalim.crmspring.repository.TeacherRepository;
import uz.iftixortalim.crmspring.repository.UserRepository;
import uz.iftixortalim.crmspring.service.AuthenticationService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final UserMapper userMapper;


    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AlreadyExists("Username already exists");
        }
        Role role = roleRepository.findByName(request.getRole()).orElseThrow(() -> new NotFoundException("Role not found"));
        User user = User
                .builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .createdAt(LocalDate.now())
                .build();
        userRepository.save(user);
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtService.generateToken(user)).build());
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AuthenticationException(Messages.AUTH_ERROR));
        StudentDTOForAuth student = null;
        TeacherDTO teacher = null;
        UserDTO userDTO = null;
        String role = user.getRole().getName();
        if (role.equals("ROLE_STUDENT")) {
            student = studentMapper.toDtoForAuth(studentRepository.findById(user.getId()).orElseThrow(() -> new AuthenticationException(Messages.AUTH_ERROR)));
        } else if (role.equals("ROLE_TEACHER")) {
            teacher = teacherMapper.toDto(teacherRepository.findById(user.getId()).orElseThrow(() -> new AuthenticationException(Messages.AUTH_ERROR)));
        } else if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SUPER_ADMIN")){
            userDTO = userMapper.toDto(userRepository.findById(user.getId()).orElseThrow(() -> new AuthenticationException(Messages.AUTH_ERROR)));
        }
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtService.generateToken(user)).teacher(teacher).student(student).user(userDTO).build());
    }
}
