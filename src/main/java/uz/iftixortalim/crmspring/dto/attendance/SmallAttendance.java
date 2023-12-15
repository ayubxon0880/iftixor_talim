package uz.iftixortalim.crmspring.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmallAttendance {
    private Long id;
    private String status;
}
