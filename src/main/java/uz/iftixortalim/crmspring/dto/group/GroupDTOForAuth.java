package uz.iftixortalim.crmspring.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTOForAuth {
    private Long id;
    private String name;
    private String teacher;
    private String phone;
}
