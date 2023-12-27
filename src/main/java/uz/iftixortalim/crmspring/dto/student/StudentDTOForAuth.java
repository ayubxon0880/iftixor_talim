package uz.iftixortalim.crmspring.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.dto.group.GroupDTOForAuth;
import uz.iftixortalim.crmspring.model.Status;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTOForAuth {
    private Long id;
    private String fullName;
    private String phone;
    private String status;
    private Set<GroupDTOForAuth> group;
    private String password;
    private String username;

    public StudentDTOForAuth(Long id, String fullName, String phone, Status status) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status.getName();
    }
}
