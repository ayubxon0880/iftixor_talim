package uz.iftixortalim.crmspring.mapper;

import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.teacher.TeacherDTO;
import uz.iftixortalim.crmspring.model.Teacher;

@Service
public class TeacherMapper {
    public TeacherDTO toDto(Teacher teacher){
        if (teacher == null) return null;
        return new TeacherDTO(
                teacher.getId(),
                teacher.getFullName(),
                teacher.getPhone()
        );
    }

    public Teacher toEntity(TeacherDTO teacherDTO){
        if (teacherDTO == null) return null;
        return new Teacher(
                teacherDTO.getId(),
                teacherDTO.getFullName(),
                teacherDTO.getPhone()
        );
    }
}
