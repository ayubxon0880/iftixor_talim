package uz.iftixortalim.crmspring.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceForSave {
    private Long groupId;
    private Map<Long,String> attendances;
}
