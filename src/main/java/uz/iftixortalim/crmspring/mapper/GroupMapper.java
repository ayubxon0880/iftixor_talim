package uz.iftixortalim.crmspring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.group.GroupDTO;
import uz.iftixortalim.crmspring.dto.group.GroupDTOForAuth;
import uz.iftixortalim.crmspring.dto.group.GroupSmallDTO;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.model.Group;
import uz.iftixortalim.crmspring.repository.TeacherRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupMapper {
    private final TeacherRepository teacherRepository;
    private final StudentMapper studentMapper;

    public GroupDTO toDto(Group group){
        if (group == null) return null;
        return new GroupDTO(
                group.getId(),
                group.getDirection(),
                group.getPayment(),
                group.getTeacher().getId(),
                group.getStartTime(),
                group.getDays(),
                group.getEndTime(),
                group.getCreatedAt(),
                group.getStudents() != null ? group.getStudents().stream().map(studentMapper::toDto).collect(Collectors.toSet()) : null
        );
    }

    public Group toEntity(GroupDTO groupDTO){
        if (groupDTO == null) return null;
        return new Group(
                groupDTO.getId(),
                groupDTO.getDirection(),
                groupDTO.getPayment(),
                teacherRepository.findById(groupDTO.getTeacherId()).orElseThrow(() -> new NotFoundException("Teacher not found")),
                groupDTO.getDays(),
                groupDTO.getStartTime(),
                groupDTO.getEndTime(),
                groupDTO.getCreatedAt(),
                null
        );
    }


    public GroupSmallDTO toSmallDto(Group group) {
        if (group == null) return null;
        return new GroupSmallDTO(
                group.getId(),
                group.getDirection(),
                group.getPayment(),
                group.getTeacher().getFullName(),
                group.getDays(),
                group.getStartTime(),
                group.getEndTime()
        );
    }

    public GroupDTOForAuth dtoForAuth(Group group) {
        if (group == null) return null;
        return new GroupDTOForAuth(
                group.getId(),
                group.getDirection(),
                group.getTeacher().getFullName(),
                group.getTeacher().getPhone()
        );
    }
}
