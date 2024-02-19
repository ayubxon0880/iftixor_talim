package uz.iftixortalim.crmspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.iftixortalim.crmspring.dto.group.GroupDTO;
import uz.iftixortalim.crmspring.dto.group.GroupDTOForAuth;
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
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody GroupDTO groupDTO){
        return groupService.create(groupDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_TEACHER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<GroupDTO> getById(@PathVariable Long id){
        return groupService.getById(id);
    }

    @GetMapping("/direction/{direction}")
    public ResponseEntity<GroupDTO> getByDirection(@PathVariable String direction){
        return groupService.getByDirection(direction);
    }

    @GetMapping("/direction-all")
    public ResponseEntity<List<GroupDTOForAuth>> getByDirection(){
        return groupService.getByDirectionAll();
    }


    @GetMapping("/student/{id}")
    public ResponseEntity<List<GroupDTOForAuth>> getByStudent(@PathVariable Long id){
        return groupService.getByStudent(id);
    }


    @GetMapping
    public ResponseEntity<List<GroupSmallDTO>> getByAll(){
        return groupService.getByAll();
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<GroupSmallDTO>> getByTeacherId(){
        return groupService.getByTeacherId();
    }

    @PutMapping
    @Validated(value = OnUpdate.class)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<GroupDTO> update(@Valid @RequestBody GroupDTO groupDTO){
        return groupService.update(groupDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return groupService.delete(id);
    }
}
















