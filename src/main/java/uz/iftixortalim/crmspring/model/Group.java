package uz.iftixortalim.crmspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direction;
    private Double payment;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    private String days;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate createdAt;
    @ManyToMany(mappedBy = "groups")
    private List<Student> students;
}
