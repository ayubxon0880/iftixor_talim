package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.QuizDTO;
import uz.iftixortalim.crmspring.dto.QuizDTON;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.mapper.QuizMapper;
import uz.iftixortalim.crmspring.model.Quiz;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.repository.QuizRepository;
import uz.iftixortalim.crmspring.service.QuizService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    @Override
    public ResponseEntity<List<QuizDTO>> getByStudentId(Long id) {
        List<QuizDTO> list = quizRepository.findByStudentId(id).stream().map(quizMapper::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<List<QuizDTO>> getByStudentId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getByStudentId(user.getId());
    }

    @Override
    public ResponseEntity<List<QuizDTO>> getByGroupId(Long id) {
        return ResponseEntity.ok(quizRepository.findByGroupId(id).stream().map(quizMapper::toDto).toList());
    }

    @Override
    public ResponseEntity<ApiResponse> save(List<QuizDTON> quizDTOList) {
        List<Quiz> quizList = quizDTOList.stream().map(quizMapper::toEntity).toList();
        quizRepository.saveAll(quizList);
        return ResponseEntity.status(201).body(ApiResponse.builder().message("Testlar muvaffaqiyatli qo'shildi").status(201).success(true).build());
    }

    @Override
    public ResponseEntity<ApiResponse> update(QuizDTON quizDTO) {
        return null;
    }
}
