package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.NewsDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;

public interface NewsService {
    ResponseEntity<ApiResponse> save(NewsDTO newsDTO);

    ResponseEntity<List<NewsDTO>> getAll();
}
