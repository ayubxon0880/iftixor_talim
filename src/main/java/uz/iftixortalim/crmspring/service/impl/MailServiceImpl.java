package uz.iftixortalim.crmspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.Mail;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.service.MailService;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public ResponseEntity<ApiResponse> sendMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ayubxonnt@gmail.com");
        message.setTo("codera1401@gmail.com");
        message.setText(mail.getPhone()+" dan.\n\n"+mail.getMessage());
        message.setSubject("Taklif va Shikoyat");

        try {
            javaMailSender.send(message);
        } catch (Exception e){
            return ResponseEntity.ok(ApiResponse.builder().message("Xabar yuborilmadi").build());
        }
        return ResponseEntity.ok(ApiResponse.builder().message("Xabar yuborildi").build());
    }
}
