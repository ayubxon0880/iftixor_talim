package uz.iftixortalim.crmspring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.SpeakingDTO;
import uz.iftixortalim.crmspring.model.Speaking;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.repository.LikeRepository;

@Service
@RequiredArgsConstructor
public class SpeakingMapper {
    private final StudentMapper studentMapper;
    private final LikeRepository likeRepository;

    public SpeakingDTO toDto(Speaking speaking){
        if (speaking == null) return null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new SpeakingDTO(
                speaking.getId(),
                speaking.getTopic() == null ? null : speaking.getTopic().getTopic(),
                studentMapper.toDto2(speaking.getSpeaker()),
                speaking.getAudioBase64(),
                likeRepository.findLikesCount(speaking.getId()),
                likeRepository.existsBySpeakingIdAndUserId(speaking.getId(), user.getId())
        );
    }
}
