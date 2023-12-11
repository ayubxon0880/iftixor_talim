package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.group.GroupDTO;
import uz.iftixortalim.crmspring.dto.group.GroupSmallDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;

import java.util.List;

public interface GroupService {
    ResponseEntity<ApiResponse> create(GroupDTO groupDTO);

    ResponseEntity<GroupDTO> getById(Long id);

    ResponseEntity<GroupDTO> update(GroupDTO groupDTO);

    ResponseEntity<ApiResponse> delete(Long id);

    ResponseEntity<List<GroupSmallDTO>> getByAll();
}
