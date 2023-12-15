package uz.iftixortalim.crmspring.mapper;

import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.TeacherDTO;
import uz.iftixortalim.crmspring.dto.TeacherDTOSave;
import uz.iftixortalim.crmspring.dto.group.GroupDTOForAuth;
import uz.iftixortalim.crmspring.model.Group;
import uz.iftixortalim.crmspring.model.Teacher;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

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
