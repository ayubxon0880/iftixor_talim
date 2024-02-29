package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.model.Topic;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.repository.TopicRepository;
import uz.iftixortalim.crmspring.service.TopicService;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    @Override
    public ResponseEntity<Topic> getRandomTopic() {
        Random random = new Random();
        List<Long> ids = topicRepository.findAllIds();
        long rand = ids.get(random.nextInt(ids.size()));
        Topic topic = topicRepository.findById(rand).orElseThrow(() -> new NotFoundException("Savol topilmadi qaytadan uringing"));
        return ResponseEntity.ok(topic);
    }

    @Override
    public ResponseEntity<List<Topic>> getAll() {
        return ResponseEntity.ok(topicRepository.findAll());
    }

    @Override
    public ResponseEntity<ApiResponse> save(String topic) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Topic topic1 = new Topic();
        topic1.setTopic(topic);
        topic1.setAuthor(user.getUsername());
        topicRepository.save(topic1);
        return ResponseEntity.ok(ApiResponse.builder().message("Topic saqlandi").status(201).success(true).build());
    }
}
