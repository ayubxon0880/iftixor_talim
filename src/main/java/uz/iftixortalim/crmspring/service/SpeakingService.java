package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.SpeakingDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;

public interface SpeakingService {
    ResponseEntity<ApiResponse> save(String data, Long topicId);

    ResponseEntity<List<SpeakingDTO>> getPopular();

    ResponseEntity<Boolean> like(Long speakingId);

}
