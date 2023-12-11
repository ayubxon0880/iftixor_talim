package uz.iftixortalim.crmspring.dto.student;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.annotations.ValidPassword;
import uz.iftixortalim.crmspring.dto.response.AuthenticationRequest;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    @NotNull
    private String fullName;
    @NotNull
    private String phone;
    private String status;
    @NotNull
    private Set<Long> groupId;
    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @ValidPassword
    private String password;
    @NotNull
    private String username;
}
