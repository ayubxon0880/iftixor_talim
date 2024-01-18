package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.group.GroupDTO;
import uz.iftixortalim.crmspring.dto.group.GroupDTOForAuth;
import uz.iftixortalim.crmspring.dto.group.GroupSmallDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.dto.student.StudentDTO;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.mapper.GroupMapper;
import uz.iftixortalim.crmspring.mapper.StudentMapper;
import uz.iftixortalim.crmspring.model.Group;
import uz.iftixortalim.crmspring.model.Student;
import uz.iftixortalim.crmspring.model.User;
import uz.iftixortalim.crmspring.repository.GroupRepository;
import uz.iftixortalim.crmspring.repository.StudentRepository;
import uz.iftixortalim.crmspring.service.GroupService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final GroupMapper groupMapper;

    @Override
    public ResponseEntity<ApiResponse> create(GroupDTO groupDTO) {
        Group group = groupMapper.toEntity(groupDTO);
        group.setCreatedAt(LocalDate.now());
        groupRepository.save(group);
        return ResponseEntity.ok(ApiResponse
                .builder()
                .status(201)
                .message("Group created")
                .success(true)
                .build());
    }

    @Override
    public ResponseEntity<GroupDTO> getById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new NotFoundException("Group not found"));
        group.setStudents(null);
        return ResponseEntity.ok(groupMapper.toDto(group));
    }

    @Override
    public ResponseEntity<GroupDTO> update(GroupDTO groupDTO) {
        if (!groupRepository.existsById(groupDTO.getId()))
            throw new NotFoundException("Group not found");
        Group group = groupMapper.toEntity(groupDTO);
        Group save = groupRepository.save(group);
        return ResponseEntity.ok(groupMapper.toDto(save));
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long id) {
        try {
            if (!groupRepository.existsById(id))
                throw new NotFoundException("Group not found");
            groupRepository.deleteById(id);
            return ResponseEntity.ok(ApiResponse.builder().status(200).message("Group deleted").success(true).build());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.builder().status(300).message("Something is wrong").success(false).build());
        }
    }

    @Override
    public ResponseEntity<List<GroupSmallDTO>> getByAll() {
        return ResponseEntity.ok(groupRepository.findAll(Sort.by("createdAt")).stream().map(groupMapper::toSmallDto).toList());
    }

    @Override
    public ResponseEntity<List<GroupSmallDTO>> getByTeacherId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<GroupSmallDTO> list = null;
        if (user.getRole().getName().equals("ROLE_TEACHER")) {
            list =  groupRepository.findByTeacherId(user.getId()).stream().map(groupMapper::toSmallDto).toList();
        } else if (user.getRole().getName().equals("ROLE_ADMIN") || user.getRole().getName().equals("ROLE_SUERP_ADMIN")) {
            list = groupRepository.findAll().stream().map(groupMapper::toSmallDto).toList();
        }
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<GroupDTO> getByDirection(String direction) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GroupDTO group = groupMapper.toDto(groupRepository.findByDirectionAndTeacherId(direction,user.getId()).orElseThrow(() -> new NotFoundException("Gurux topilmadi")));
        return ResponseEntity.ok(group);
    }

    @Override
    public ResponseEntity<List<GroupDTOForAuth>> getByDirectionAll() {
        List<GroupDTOForAuth> list = groupRepository.findAll().stream().map(groupMapper::dtoForAuth).toList();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<List<GroupDTOForAuth>> getByStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student topilmadi"));
        List<GroupDTOForAuth> list = student.getGroups().stream().map(groupMapper::dtoForAuth).toList();
        return ResponseEntity.ok(list);
    }
}
