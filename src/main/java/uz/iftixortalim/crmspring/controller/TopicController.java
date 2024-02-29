package uz.iftixortalim.crmspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.model.Topic;
import uz.iftixortalim.crmspring.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topic")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody Topic topic){
        return topicService.save(topic.getTopic());
    }
    @GetMapping("/random")
    public ResponseEntity<Topic> getRandomTopic(){
        return topicService.getRandomTopic();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Topic>> getAll(){
        return topicService.getAll();
    }

}