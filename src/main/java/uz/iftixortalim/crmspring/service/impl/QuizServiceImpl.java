package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.quiz.QuizDTO;
import uz.iftixortalim.crmspring.dto.quiz.QuizDTON;
import uz.iftixortalim.crmspring.dto.quiz.QuizList;
import uz.iftixortalim.crmspring.dto.Test;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.mapper.QuizMapper;
import uz.iftixortalim.crmspring.model.*;
import uz.iftixortalim.crmspring.repository.GroupRepository;
import uz.iftixortalim.crmspring.repository.QuizRepository;
import uz.iftixortalim.crmspring.repository.StudentRepository;
import uz.iftixortalim.crmspring.service.QuizService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final QuizMapper quizMapper;
    private final String ZONE = "Asia/Tokyo";



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
    public ResponseEntity<List<QuizDTO>> getByGroupId(Long id, LocalDate first) {
        List<QuizDTO> list = quizRepository.findByGroupIdAndTestDate(id, first).stream().map(quizMapper::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<ApiResponse> save(QuizList quizList) {
        Map<Long,Integer> testlar = new HashMap<>();

        for (Test test : quizList.getTests()) {
            testlar.put(test.getId(),test.getCorrect());
        }
        List<Quiz> quizzes = new ArrayList<>();
        Group group = groupRepository.findById(quizList.getGroupId()).orElseThrow();

        for (Student student: group.getStudents()) {
            Quiz quiz = new Quiz(
                    null,
                    student,
                    group,
                    LocalDate.now(ZoneId.of(ZONE)),
                    quizList.getTestCount(),
                    testlar.get(student.getId()) == null ? 0 : testlar.get(student.getId())
            );

            quizzes.add(quiz);
        }
        quizRepository.saveAll(quizzes);
        return ResponseEntity.status(201).body(ApiResponse.builder().message("Testlar muvaffaqiyatli qo'shildi").status(201).success(true).build());
    }

    @Override
    public ResponseEntity<ApiResponse> update(QuizDTON quizDTO) {
        return null;
    }
}
