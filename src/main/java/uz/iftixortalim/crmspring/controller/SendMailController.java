package uz.iftixortalim.crmspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.iftixortalim.crmspring.dto.Mail;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.service.MailService;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class SendMailController {
    private final MailService mailService;
    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendMail(Mail mail){
        return mailService.sendMail(mail);
    }
}
