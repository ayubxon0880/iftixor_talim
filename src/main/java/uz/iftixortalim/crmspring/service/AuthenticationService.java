package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.response.AuthenticationRequest;
import uz.iftixortalim.crmspring.dto.response.AuthenticationResponse;
import uz.iftixortalim.crmspring.dto.response.RegisterRequest;

public interface AuthenticationService {
    ResponseEntity<AuthenticationResponse> register(RegisterRequest request);
    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
