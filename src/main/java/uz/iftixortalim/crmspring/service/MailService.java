package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.Mail;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

public interface MailService {
    ResponseEntity<ApiResponse> sendMail(Mail mail);
}
