package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.quiz.QuizDTO;
import uz.iftixortalim.crmspring.dto.quiz.QuizDTON;
import uz.iftixortalim.crmspring.dto.quiz.QuizList;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;
import uz.iftixortalim.crmspring.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
@Validated
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/student/{id}")
    public ResponseEntity<List<QuizDTO>> getByStudentId(@PathVariable Long id){
        return quizService.getByStudentId(id);
    }

    @GetMapping("/student")
    public ResponseEntity<List<QuizDTO>> getByStudentId(){
        return quizService.getByStudentId();
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<List<QuizDTO>> getByGroupId(@PathVariable Long id){
        return quizService.getByGroupId(id);
    }

    @PostMapping("/save")
    @Validated(value = OnCreate.class)
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody QuizList quizList) {
        return quizService.save(quizList);
    }

    @PutMapping
    @Validated(value = OnUpdate.class)
        public ResponseEntity<ApiResponse> update(@Valid @RequestBody QuizDTON quizDTO) {
        return quizService.update(quizDTO);
    }


}
