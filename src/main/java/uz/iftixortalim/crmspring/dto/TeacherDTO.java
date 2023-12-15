package uz.iftixortalim.crmspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.annotations.ValidPassword;
import uz.iftixortalim.crmspring.dto.group.GroupDTOForAuth;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;
import uz.iftixortalim.crmspring.model.Group;

import java.util.Set;

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
    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @ValidPassword
    private String password;
    @NotNull
    private String username;
    private Set<GroupDTOForAuth> groups;

    public TeacherDTO(Long id, String fullName, String phone, Set<GroupDTOForAuth> groupDTOForAuth) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.groups = groupDTOForAuth;
    }

    public TeacherDTO(Long id, String fullName, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
    }


}
