package uz.iftixortalim.crmspring.dto.quiz;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.dto.Test;
import uz.iftixortalim.crmspring.model.Status;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizList {
    @NotNull
    private Long groupId;
    @NotNull
    private Integer testCount;
    @NotNull
    private List<Test> tests;
}
