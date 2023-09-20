package CSS.ReservationSystem.dto;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.domain.Role;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;

    private String token;

}
