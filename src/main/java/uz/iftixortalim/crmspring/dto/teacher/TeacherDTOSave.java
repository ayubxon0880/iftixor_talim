package uz.iftixortalim.crmspring.dto.teacher;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDTOSave {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private String phone;
    @NotNull
    private Double salary;
}
