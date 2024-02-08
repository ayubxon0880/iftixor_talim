package uz.iftixortalim.crmspring.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public ResponseEntity<?> takeExam(QuestionListDTO questionListDTO) {
        System.out.println(questionListDTO);
        return null;
    }

    @Override
    public ResponseEntity<Question> findById(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("Savol topilmadi"));
        question.setAnswers(question.getAnswers().stream().map(it -> new Answer(it.getId(),it.getAnswer(),true)).toList());
        return ResponseEntity.ok(question);
    }
}
