package uz.iftixortalim.crmspring.questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionListDTO {
    private Long id;
    private HashMap<Long,Long> questions;
}
