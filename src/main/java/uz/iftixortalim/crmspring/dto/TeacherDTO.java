package uz.iftixortalim.crmspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.annotations.ValidPassword;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDTO {
    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;
    @NotNull
    private String fullName;
    @NotNull
    private String phone;
    @NotNull
    private Double salary;
    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @ValidPassword
    private String password;
    @NotNull
    private String username;

    public TeacherDTO(Long id, String fullName, String phone, Double salary) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.salary = salary;
    }
}
