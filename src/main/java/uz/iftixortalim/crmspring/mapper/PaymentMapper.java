package uz.iftixortalim.crmspring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.PaymentDTO;
import uz.iftixortalim.crmspring.dto.PaymentDTOForSave;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.model.Payment;
import uz.iftixortalim.crmspring.repository.GroupRepository;
import uz.iftixortalim.crmspring.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class PaymentMapper {
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public PaymentDTO toDto(Payment payment){
        if (payment == null) return null;
        return new PaymentDTO(
                payment.getId(),
                payment.getPaymentDate(),
                studentMapper.toDto(payment.getStudent()),
                payment.getGroup().getDirection(),
                payment.getPayment(),
                payment.getIsPayed()
        );
    }

    public Payment toEntity(PaymentDTOForSave paymentDTO){
        if (paymentDTO == null) return null;
        return new Payment(
                paymentDTO.getId(),
                paymentDTO.getPaymentDate(),
                studentRepository.findById(paymentDTO.getStudentId()).orElseThrow(() -> new NotFoundException("Student not found")),
                groupRepository.findById(paymentDTO.getGroupId()).orElseThrow(() -> new NotFoundException("Group not found")),
                paymentDTO.getPayment(),
                paymentDTO.getIsPayed()
        );
    }
}
