package uz.iftixortalim.crmspring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iftixortalim.crmspring.dto.student.StudentDTOForAuth;
import uz.iftixortalim.crmspring.dto.teacher.TeacherDTO;
import uz.iftixortalim.crmspring.dto.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthenticationResponse {
    private String token;
    private UserDTO user;
    private StudentDTOForAuth student;
    private TeacherDTO teacher;
}
