package uz.iftixortalim.crmspring.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/take-exam")
    public ResponseEntity<?> takeExam(@RequestBody QuestionListDTO questionListDTO){
        return questionService.takeExam(questionListDTO);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long questionId){
        return questionService.findById(questionId);
    }
}
