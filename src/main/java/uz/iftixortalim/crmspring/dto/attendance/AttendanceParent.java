package uz.iftixortalim.crmspring.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceParent {
    private LocalDate date;
    private List<AttendanceSmallDTO> attendanceSmallDTO;
}
