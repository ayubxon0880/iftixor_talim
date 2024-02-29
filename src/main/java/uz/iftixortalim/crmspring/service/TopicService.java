package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.model.Topic;

import java.util.List;

public interface TopicService {
    ResponseEntity<Topic> getRandomTopic();

    ResponseEntity<List<Topic>> getAll();

    ResponseEntity<ApiResponse> save(String topic);
}
