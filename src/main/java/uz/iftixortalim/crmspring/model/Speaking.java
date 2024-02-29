package uz.iftixortalim.crmspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Speaking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student speaker;
    @Lob
    private String audioBase64;
    @OneToMany(mappedBy = "speaking")
    private List<Like> likes;

}