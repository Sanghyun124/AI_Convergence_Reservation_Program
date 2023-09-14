package CSS.ReservationSystem.dto;

import CSS.ReservationSystem.domain.Role;
import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    private Integer studentId;

    private String password;

}
