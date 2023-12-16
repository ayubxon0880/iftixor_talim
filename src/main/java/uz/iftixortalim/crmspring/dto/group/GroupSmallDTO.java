package uz.iftixortalim.crmspring.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupSmallDTO {
    private Long id;
    private String direction;
    private Double payment;
    private String teacher;
    private String days;
    private LocalTime start;
    private LocalTime end;
}
