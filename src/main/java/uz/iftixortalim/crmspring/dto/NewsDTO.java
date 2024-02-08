package uz.iftixortalim.crmspring.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    private String imagePath;
    private String title;
    private String body;
    @Null
    private List<String> hashtag;
    private LocalDate createdAt;

}
