package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.PaymentDTO;
import uz.iftixortalim.crmspring.dto.PaymentDTOForSave;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    ResponseEntity<ApiResponse> create(PaymentDTOForSave paymentDTOForSave);

    ResponseEntity<PaymentDTO> getById(Long id);

    ResponseEntity<PaymentDTO> update(PaymentDTOForSave paymentDTOForSave);

    ResponseEntity<ApiResponse> delete(Long id);

    ResponseEntity<List<PaymentDTO>> getByStudentId(Long id, Optional<Integer> year);
}
