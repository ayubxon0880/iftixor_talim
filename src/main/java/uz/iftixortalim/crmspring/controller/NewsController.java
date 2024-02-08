package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.NewsDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody NewsDTO newsDTO){
        return newsService.save(newsDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<NewsDTO>> getAll(){
        return newsService.getAll();
    }


}
