package uz.iftixortalim.crmspring.questions;

import org.springframework.http.ResponseEntity;

public interface QuestionService {
    ResponseEntity<?> takeExam(QuestionListDTO questionListDTO);

    ResponseEntity<Question> findById(Long questionId);
}
