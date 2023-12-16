package uz.iftixortalim.crmspring.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTOForSave {
    private Long value;
    private String label;
}
