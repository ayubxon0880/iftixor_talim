package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.PaymentDTO;
import uz.iftixortalim.crmspring.dto.PaymentDTOForSave;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;
import uz.iftixortalim.crmspring.service.PaymentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Validated
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @Validated(value = OnCreate.class)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody PaymentDTOForSave paymentDTOForSave){
        return paymentService.create(paymentDTOForSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getById(@PathVariable Long id){
        return paymentService.getById(id);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<PaymentDTO>> getByStudentId(@PathVariable Long id,@RequestParam Optional<Integer> year){
        return paymentService.getByStudentId(id,year);
    }

    @PutMapping
    @Validated(value = OnUpdate.class)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<PaymentDTO> update(@Valid @RequestBody PaymentDTOForSave paymentDTOForSave){
        return paymentService.update(paymentDTOForSave);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return paymentService.delete(id);
    }
}














