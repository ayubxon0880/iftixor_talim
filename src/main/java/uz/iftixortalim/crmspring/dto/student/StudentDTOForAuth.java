package uz.iftixortalim.crmspring.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.dto.group.GroupDTOForAuth;

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
}
