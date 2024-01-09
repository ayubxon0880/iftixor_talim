package uz.iftixortalim.crmspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorityDTO {
    private Long id;
    private String name;
}
