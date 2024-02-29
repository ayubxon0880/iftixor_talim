package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.SpeakingDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.mapper.SpeakingMapper;
import uz.iftixortalim.crmspring.model.*;
import uz.iftixortalim.crmspring.repository.LikeRepository;
import uz.iftixortalim.crmspring.repository.SpeakingRepository;
import uz.iftixortalim.crmspring.repository.StudentRepository;
import uz.iftixortalim.crmspring.repository.TopicRepository;
import uz.iftixortalim.crmspring.service.SpeakingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeakingServiceImpl implements SpeakingService {
    private final TopicRepository topicRepository;
    private final LikeRepository likeRepository;
    private final SpeakingRepository speakingRepository;
    private final StudentRepository studentRepository;
    private final SpeakingMapper speakingMapper;

    @Override
    public ResponseEntity<ApiResponse> save(String data, Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new NotFoundException("Topic not found"));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentRepository.findById(user.getId()).get();
        String base64data = data.replace("%2F", "/");
        Speaking speaking = new Speaking();
        speaking.setSpeaker(student);
        speaking.setTopic(topic);
        speaking.setAudioBase64(base64data);
        speakingRepository.save(speaking);
        return ResponseEntity.ok(ApiResponse.builder().message("Audio saved").status(201).success(true).build());
    }

    @Override
    public ResponseEntity<List<SpeakingDTO>> getPopular() {
        List<SpeakingDTO> list =
//                speakingRepository.findAllByLikesGreaterThan(0L)
                speakingRepository.findAll()
                        .stream().map(speakingMapper::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Boolean> like(Long speakingId) {
        Speaking speaking = speakingRepository.findById(speakingId).orElseThrow();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (likeRepository.existsBySpeakingIdAndUserId(speaking.getId(), user.getId())) {
            Like like = likeRepository.findBySpeakingIdAndUserId(speaking.getId(), user.getId()).get();
            likeRepository.delete(like);
            return ResponseEntity.ok(false);
        }

        Like like = new Like();
        like.setSpeaking(speaking);
        like.setUser(user);

        likeRepository.save(like);
        return ResponseEntity.ok(true);
    }



/*
    private Response uploadFile(MultipartFile file){
        String apiUrl = "https://api.bytescale.com/v2/accounts/FW25bs8/uploads/form_data";

        WebClient webClient = WebClient.create();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer secret_FW25bs889d2QWwK9NwqCV5Gpc8MG");

        byte[] fileContent;
        try {
            fileContent = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayResource fileResource = new ByteArrayResource(fileContent) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        String response = webClient.post()
                .uri(apiUrl)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromMultipartData("file", fileResource))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Gson gson = new Gson();
        ResponseFile responseFile = gson.fromJson(response, ResponseFile.class);
        return responseFile.getFiles().get(responseFile.getFiles().size()-1);
    }
 */
}
