package uz.iftixortalim.crmspring.dto.quiz;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizDTON {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    @NotNull
    private Long studentId;
    @NotNull
    private Long groupId;
    @Null
    private LocalDate quizDate;
    @NotNull
    private Integer testCount;
    @NotNull
    private Integer correctAnswer;
    @Null
    private Integer wrongAnswer;
    @NotNull
    private String degree;
}
