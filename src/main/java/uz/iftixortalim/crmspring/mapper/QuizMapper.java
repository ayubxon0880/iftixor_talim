package uz.iftixortalim.crmspring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.quiz.QuizDTO;
import uz.iftixortalim.crmspring.dto.quiz.QuizDTON;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.model.Quiz;
import uz.iftixortalim.crmspring.repository.GroupRepository;
import uz.iftixortalim.crmspring.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class QuizMapper {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;
    private final GroupMapper groupMapper;

    public QuizDTO toDto(Quiz quiz){
        return new QuizDTO(
                quiz.getId(),
                studentMapper.toDto(quiz.getStudent()),
                quiz.getGroup() == null ? null : quiz.getGroup().getDirection(),
                quiz.getTestDate(),
                quiz.getTestCount(),
                quiz.getCorrect(),
                quiz.getTestCount() - quiz.getCorrect()
        );
    }

    public Quiz toEntity(QuizDTO quizDTO){
        return new Quiz(
                quizDTO.getId(),
                studentMapper.toEntity(quizDTO.getStudent()),
                null,
                quizDTO.getTestDate(),
                quizDTO.getTestCount(),
                quizDTO.getCorrect()
        );
    }

    public Quiz toEntity(QuizDTON quizDTO){
        return new Quiz(
                quizDTO.getId(),
                studentRepository.findById(quizDTO.getStudentId()).orElseThrow(() -> new NotFoundException("Student topilmadi")),
                groupRepository.findById(quizDTO.getGroupId()).orElseThrow(() -> new NotFoundException("Gurux topilmadi")),
                quizDTO.getQuizDate(),
                quizDTO.getTestCount(),
                quizDTO.getCorrectAnswer()
        );
    }


}
