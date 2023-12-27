package uz.iftixortalim.crmspring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    private String name;
    private String phone;
    private String message;
}
