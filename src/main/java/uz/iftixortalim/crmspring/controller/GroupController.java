package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.group.GroupDTO;
import uz.iftixortalim.crmspring.dto.group.GroupSmallDTO;
import uz.iftixortalim.crmspring.dto.response.ApiResponse;
import uz.iftixortalim.crmspring.group.OnCreate;
import uz.iftixortalim.crmspring.group.OnUpdate;
import uz.iftixortalim.crmspring.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
@Validated
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    @Validated(value = OnCreate.class)
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody GroupDTO groupDTO){
        return groupService.create(groupDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getById(@PathVariable Long id){
        return groupService.getById(id);
    }
    @GetMapping
    public ResponseEntity<List<GroupSmallDTO>> getByAll(){
        return groupService.getByAll();
    }

    @PutMapping
    @Validated(value = OnUpdate.class)
    public ResponseEntity<GroupDTO> update(@Valid @RequestBody GroupDTO groupDTO){
        return groupService.update(groupDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return groupService.delete(id);
    }
}
