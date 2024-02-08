package uz.iftixortalim.crmspring.mapper;

import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.NewsDTO;
import uz.iftixortalim.crmspring.model.Hashtag;
import uz.iftixortalim.crmspring.model.News;

@Service
public class NewsMapper {

    public NewsDTO toDto(News news){
        if (news == null) return null;
        return new NewsDTO(
                news.getId(),
                news.getImagePath(),
                news.getTitle(),
                news.getBody(),
                news.getHashtag() == null ? null : news.getHashtag().stream().map(Hashtag::getName).toList(),
                news.getCreatedAt()
        );
    }

    public News toEntity(NewsDTO newsDTO){
        if (newsDTO == null) return null;
        return new News(
                newsDTO.getId(),
                newsDTO.getImagePath(),
                newsDTO.getTitle(),
                newsDTO.getBody(),
                newsDTO.getHashtag() == null ? null : newsDTO.getHashtag().stream().map(it -> new Hashtag(null,it)).toList(),
                newsDTO.getCreatedAt()
        );
    }


}
