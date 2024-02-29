package uz.iftixortalim.crmspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.SpeakingDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.service.SpeakingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speaking")
@RequiredArgsConstructor
public class SpeakingController {
    private final SpeakingService speakingService;

    @PostMapping("/save/{topicId}")
    public ResponseEntity<ApiResponse> saveSpeaking(@PathVariable Long topicId, @RequestBody String data){
        return speakingService.save(data,topicId);
    }

    @GetMapping("/get-popular")
    public ResponseEntity<List<SpeakingDTO>> getPopular(){
        return speakingService.getPopular();
    }

    @GetMapping("/like/{speakingId}")
    public ResponseEntity<Boolean> like(@PathVariable Long speakingId){
        return speakingService.like(speakingId);
    }

}
