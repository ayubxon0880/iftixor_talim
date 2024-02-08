package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.NewsDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.mapper.NewsMapper;
import uz.iftixortalim.crmspring.model.News;
import uz.iftixortalim.crmspring.repository.NewsRepository;
import uz.iftixortalim.crmspring.service.NewsService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsMapper newsMapper;
    private final NewsRepository newsRepository;
    private final String ZONE = "Asia/Tokyo";

    @Override
    public ResponseEntity<ApiResponse> save(NewsDTO newsDTO) {
        News news = newsMapper.toEntity(newsDTO);
        news.setCreatedAt(LocalDate.now(ZoneId.of(ZONE)));
        newsRepository.save(news);
        return ResponseEntity.ok(ApiResponse.builder().message("Yangilik yaratildi").success(true).status(201).success(true).build());
    }

    @Override
    public ResponseEntity<List<NewsDTO>> getAll() {
        List<NewsDTO> list = newsRepository.findAll().stream().map(newsMapper::toDto).toList();
        return ResponseEntity.ok(list);
    }
}
