package uz.iftixortalim.crmspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {
    private Long id;
    private String name;
    private List<String> authorities;
}
