package uz.iftixortalim.crmspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeakingDTO {
    private Long id;
    private String topic;
    private StudentDTO speaker;
    private String base64;
    private Long likes;
    private Boolean isLiked;
}
