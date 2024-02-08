package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.config.JwtService;
import uz.iftixortalim.crmspring.dto.UserDTO;
import uz.iftixortalim.crmspring.dto.response.AuthenticationRequest;
import uz.iftixortalim.crmspring.dto.response.AuthenticationResponse;
import uz.iftixortalim.crmspring.dto.response.RegisterRequest;
import uz.iftixortalim.crmspring.exception.AuthenticationException;
import uz.iftixortalim.crmspring.exception.Messages;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.exception.AlreadyExists;
import uz.iftixortalim.crmspring.mapper.UserMapper;
import uz.iftixortalim.crmspring.model.Role;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.repository.*;
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
        UserDTO userDTO = userMapper.toDto(user);
        return ResponseEntity.ok(
                AuthenticationResponse
                        .builder()
                        .token(jwtService.generateToken(user))
                        .user(userDTO)
                        .build());
    }
}
