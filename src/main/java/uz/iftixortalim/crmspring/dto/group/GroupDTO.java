package uz.iftixortalim.crmspring.dto.group;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDTO {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    @NotNull
    private String direction;
    @NotNull
    private Double payment;
    @NotNull
    private String degree;
    @NotNull
    private Long teacherId;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private String days;
    @NotNull
    private LocalTime endTime;
    @Null
    private LocalDate createdAt;
    @Null
    private Set<StudentDTO> students;
}
