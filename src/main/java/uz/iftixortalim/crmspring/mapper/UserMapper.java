package uz.iftixortalim.crmspring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.RoleDTO;
import uz.iftixortalim.crmspring.dto.UserDTO;
import uz.iftixortalim.crmspring.model.User;

@Service
@RequiredArgsConstructor
public class UserMapper {
    public UserDTO toDto(User user){
        return user == null ? null : new UserDTO(
                user.getId(),
                user.getUsername(),
                "",
                user.getCreatedAt(),
                new RoleDTO(user.getRole().getId(),user.getRole().getName(),null)
        );
    }
}
