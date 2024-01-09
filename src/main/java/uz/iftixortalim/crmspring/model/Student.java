package uz.iftixortalim.crmspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private Long id;
    private String fullName;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToMany
    @JoinTable(
            name = "student_group",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups;

    public Student(Long id, String fullName, String phone, Status status) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
    }
}
