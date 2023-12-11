package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.group.GroupDTO;
import uz.iftixortalim.crmspring.dto.group.GroupSmallDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.exception.NotFoundException;
import uz.iftixortalim.crmspring.mapper.GroupMapper;
import uz.iftixortalim.crmspring.model.Group;
import uz.iftixortalim.crmspring.repository.GroupRepository;
import uz.iftixortalim.crmspring.service.GroupService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
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
        GroupDTO dto = groupMapper.toDto(group);
        return ResponseEntity.ok(dto);
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
}
