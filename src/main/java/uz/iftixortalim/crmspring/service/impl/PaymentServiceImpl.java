package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.PaymentDTO;
import uz.iftixortalim.crmspring.dto.PaymentDTOForSave;
import uz.iftixortalim.crmspring.mapper.PaymentMapper;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.model.Payment;
import uz.iftixortalim.crmspring.repository.PaymentRepository;
import uz.iftixortalim.crmspring.service.PaymentService;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final String ZONE = "Asia/Tokyo";

    @Override
    public ResponseEntity<ApiResponse> create(PaymentDTOForSave paymentDTOForSave) {
        Payment entity = paymentMapper.toEntity(paymentDTOForSave);
        paymentRepository.save(entity);
        return ResponseEntity.ok(ApiResponse.builder().message("Payment created").status(201).success(true).build());
    }

    @Override
    public ResponseEntity<PaymentDTO> getById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found"));
        return ResponseEntity.ok(paymentMapper.toDto(payment));
    }

    @Override
    public ResponseEntity<PaymentDTO> update(PaymentDTOForSave paymentDTOForSave) {
        if (!paymentRepository.existsById(paymentDTOForSave.getId())) {
            throw new NotFoundException("Payment not found");
        }
        Payment payment = paymentMapper.toEntity(paymentDTOForSave);
        Payment save = paymentRepository.save(payment);
        return ResponseEntity.ok(paymentMapper.toDto(save));
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long id) {
        try {
            if (!paymentRepository.existsById(id)) {
                throw new NotFoundException("Payment not found");
            }
            paymentRepository.deleteById(id);
            return ResponseEntity.ok(ApiResponse.builder().success(true).status(200).message("Payment deleted").build());
        } catch (Exception e){
            return ResponseEntity.status(300).body(ApiResponse.builder().success(false).status(300).message("Something is wrong").build());
        }
    }

    @Override
    public ResponseEntity<List<PaymentDTO>> getByStudentId(Long id, Optional<Integer> year) {
        LocalDate currentDate = LocalDate.now(ZoneId.of(ZONE)); // JST = Asian/Tokyo
        int currentYear = year.orElseGet(currentDate::getYear);
        LocalDate first = LocalDate.of(currentYear, Month.JANUARY, 1);
        LocalDate last = LocalDate.of(currentYear, Month.DECEMBER, Month.DECEMBER.length(currentDate.isLeapYear()));

        List<PaymentDTO> payments = paymentRepository.findByStudentIdAndPaymentDateBetweenOrderByPaymentDateDesc(id,first,last).stream().map(paymentMapper::toDto).toList();
        return ResponseEntity.ok(payments);
    }
}
