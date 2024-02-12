package uz.iftixortalim.crmspring.dto.student;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.annotations.ValidPassword;
import uz.iftixortalim.crmspring.dto.group.GroupDTOForSave;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTOForSave {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    @NotNull
    private String fullName;
//    @NotNull
    private String phone;
    @Null(groups = OnCreate.class)
    private String status;
    @NotNull
    private List<Long> groups;
//    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
//    @ValidPassword
    private String password;
//    @NotNull
    private String username;

    public StudentDTOForSave(String fullName, String phone, String status, List<Long> groups, String username) {
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
        this.groups = groups;
        this.username = username;
    }
}
