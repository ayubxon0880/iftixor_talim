package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.QuizDTO;
import uz.iftixortalim.crmspring.dto.QuizDTON;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;

public interface QuizService {
    ResponseEntity<List<QuizDTO>> getByStudentId(Long id);
    ResponseEntity<List<QuizDTO>> getByStudentId();

    ResponseEntity<List<QuizDTO>> getByGroupId(Long id);

    ResponseEntity<ApiResponse> save(List<QuizDTON> quizDTOList);

    ResponseEntity<ApiResponse> update(QuizDTON quizDTO);
}
