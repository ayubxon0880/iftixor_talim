package uz.iftixortalim.crmspring.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTOForSave {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    @NotNull
    private LocalDate paymentDate;
    @NotNull
    private Long studentId;
    @NotNull
    private Long groupId;
    @NotNull
    private Double payment;
    private Boolean isPayed;
}
