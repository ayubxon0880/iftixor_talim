package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Payment;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentIdAndPaymentDateBetweenOrderByPaymentDateDesc(Long student_id, LocalDate first, LocalDate last);
}
