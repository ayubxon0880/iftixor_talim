package uz.iftixortalim.crmspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private Long id;
    private LocalDate paymentDate;
    private StudentDTO student;
    private String group;
    private Double payment;
    private Boolean isPayed;
}
