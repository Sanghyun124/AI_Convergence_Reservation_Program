package CSS.ReservationSystem.dto;

import CSS.ReservationSystem.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

    private Integer studentId;

    private String password;

    private String name;

    private Role role;

    private String email;

}
