package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.quiz.QuizDTO;
import uz.iftixortalim.crmspring.dto.quiz.QuizDTON;
import uz.iftixortalim.crmspring.dto.quiz.QuizList;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.time.LocalDate;
import java.util.List;

public interface QuizService {
    ResponseEntity<List<QuizDTO>> getByStudentId(Long id);
    ResponseEntity<List<QuizDTO>> getByStudentId();
    ResponseEntity<List<QuizDTO>> getByGroupId(Long id, LocalDate first);
    ResponseEntity<ApiResponse> save(QuizList quizList);
    ResponseEntity<ApiResponse> update(QuizDTON quizDTO);
}
