package CSS.ReservationSystem.dto;

import CSS.ReservationSystem.domain.Role;
import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    private String studentId;

    private String password;

    private Role role;

}
