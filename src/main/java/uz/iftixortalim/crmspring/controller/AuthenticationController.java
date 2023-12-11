package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.response.AuthenticationRequest;
import uz.iftixortalim.crmspring.dto.response.AuthenticationResponse;
import uz.iftixortalim.crmspring.dto.response.RegisterRequest;
import uz.iftixortalim.crmspring.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register-user")
    public ResponseEntity<AuthenticationResponse> registerUserByAdmin(@Valid @RequestBody RegisterRequest request){
        return authenticationService.register(request);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return authenticationService.authenticate(request);
    }


}
