package uz.iftixortalim.crmspring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.group.GroupDTOForAuth;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;
import uz.iftixortalim.crmspring.dto.student.StudentDTOForAuth;
import uz.iftixortalim.crmspring.dto.student.StudentDTOForSave;
import uz.iftixortalim.crmspring.dto.student.StudentSmallDto;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.model.Group;
import uz.iftixortalim.crmspring.model.Student;
import uz.iftixortalim.crmspring.repository.GroupRepository;
import uz.iftixortalim.crmspring.repository.StatusRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentMapper {
    private final StatusRepository statusRepository;
    private final GroupRepository groupRepository;

    public StudentDTO toDto(Student student){
        if (student == null) return null;

        return new StudentDTO(
                student.getId(),
                student.getFullName(),
                student.getPhone(),
                student.getStatus().getName(),
                student.getGroups().stream().map(Group::getId).collect(Collectors.toSet()),
                null,
                null
        );
    }

    public StudentDTO toDto2(Student student){
        if (student == null) return null;

        return new StudentDTO(
                student.getId(),
                student.getFullName(),
                student.getPhone(),
                student.getStatus().getName(),
                null,
                null,
                null
        );
    }

    public StudentDTOForAuth toDtoForAuth(Student student){
        if (student == null) return null;

        return new StudentDTOForAuth(
                student.getId(),
                student.getFullName(),
                student.getPhone(),
                student.getStatus().getName(),
                student.getGroups().stream().map(this::toDtoForAuth).collect(Collectors.toSet()),
                null,
                null
        );
    }

    private GroupDTOForAuth toDtoForAuth(Group group){
        if (group == null) return null;
        return new GroupDTOForAuth(
                group.getId(),
                group.getDirection(),
                group.getTeacher().getFullName(),
                group.getTeacher().getPhone()
        );
    }

    public StudentDTOForSave toSmallDto(Student student) {
        if (student == null) return null;
        return new StudentDTOForSave(
                student.getFullName(),
                student.getPhone(),
                student.getStatus().getName(),
                null,
                null

        );
    }

    public Student toEntity(StudentDTO studentDTO){
        if (studentDTO == null) return null;
        return new Student(
                studentDTO.getId(),
                studentDTO.getFullName(),
                studentDTO.getPhone(),
                statusRepository.findByName(studentDTO.getStatus()).orElseThrow(()->new NotFoundException("Status not found")),
                studentDTO.getGroupId().stream().map(it -> groupRepository.findById(it).orElseThrow(() -> new NotFoundException("Group not found"))).collect(Collectors.toSet())
        );
    }

    public Student toEntity(StudentDTOForSave studentDTO){
        if (studentDTO == null) return null;
        return new Student(
                studentDTO.getId(),
                studentDTO.getFullName(),
                studentDTO.getPhone(),
                statusRepository.findByName(studentDTO.getStatus()).orElseThrow(()->new NotFoundException("Status not found")),
                studentDTO.getGroups().stream().map(it -> groupRepository.findById(it).orElseThrow(() -> new NotFoundException("Group not found"))).collect(Collectors.toSet())
        );
    }

}
