package uz.iftixortalim.crmspring.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizSmall {
    private Long id;
    private String student;
    private String group;
    private Integer testCount;
    private Integer correctAnswer;
    private Integer wrongAnswer;
}
