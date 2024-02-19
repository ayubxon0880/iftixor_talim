package uz.iftixortalim.crmspring.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceBig {
    private String month;
    private List<AttendanceSmall> childAttendance;
}
