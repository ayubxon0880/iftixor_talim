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
    public ResponseEntity<List<QuizDTO>> getByGroupId(Long id) {
        return ResponseEntity.ok(quizRepository.findByGroupId(id).stream().map(quizMapper::toDto).toList());
    }

    @Override
    public ResponseEntity<ApiResponse> save(QuizList quizList) {
        Map<Long,Integer> testlar = new HashMap<>();
        Map<Long,String> statuslar = new HashMap<>();

        for (Test test : quizList.getTests()) {
            testlar.put(test.getId(),test.getCorrect());
        }
        for (Status status : quizList.getStatus()) {
            statuslar.put(status.getId(), status.getName());
        }
        List<Quiz> quizzes = new ArrayList<>();
        Group group = groupRepository.findById(quizList.getGroupId()).orElseThrow();

        for (Long key: testlar.keySet()) {
            Student student = studentRepository.findById(key).orElseThrow(() -> new NotFoundException("Student topilmadi"));
            Quiz quiz = new Quiz(
                    null,
                    student,
                    group,
                    LocalDate.now(ZoneId.of(ZONE)),
                    quizList.getTestCount(),
                    testlar.get(key),
                    statuslar.get(key)
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
/*

rozali_007
mohichehra_007
habibullo_007
feruzbek_007
dilnoza_007
abdulaziz_007
xurshida_007
mohinur_007
shoxruza_007
ruhshona_007
abdulatifxon_007
yoldoshali_007
mohinur_07
feruza_007
gulsanam_007
nargiza_007
hulkaroy_007
sarkorbek_007
umida_007
boymatmirzo_007
sevara_007
durdona_007
saddullabek_007
abdulaziz_07
ayubxon0880
zahro_007
sevinch_007
mubina_007
mavluda_007
xojaxonova_007
yusupova_007
abduqahhorova
abduhalilova
holmatov
sultonova
yoldoshaliyeva
alisherova
keldiyev
mamajonova
muxtorova
abdusattorova
rahmonov
sultonova_007
fayzullayeva
muhiddinov
murotaliyeva
hikmatullayeva
nasriddinova
jorayev
aliqoziyeva
islomiddinova
komiljonova
mominova
kamoliddinova
azimjonova
numonaliyeva
erkinov
mamuraliyeva
abdullayeva
nasriddinov
bekmirzayeva
anvarjonova
islomova
rahmonova
umarova
saminova
xalilubloyeva
mamuraliyeva_007
hayitova
jabboraliyeva
qodirova
akbarova
habibullayeva
akbarova_007
esonboyeva
abdugafforova
hoshimova
abdurahimov
botirov
akbarov
abduqasimova
asliddin_79
dilfuza_02
ahror_80
gulchiroy_02
shukrona_25
hojiakbar_20
azizbek_83
faxriddin
dilshodjon
zarifaxon
dildora
hilola
dildora_1
behruz
oyatullo
ilhomjon
azizbek
abduqodir
abdurahmon
lazizbek
dilyorbek
muhammadzohir
abduhafiz
behruz_1
abdumajid
sodiqova
husnidabonu
umarxojayeva
muazzamxon
mushtariy
aziza
abdulloh
elyorbek_007
diyora_007
nurhayot_007


*/