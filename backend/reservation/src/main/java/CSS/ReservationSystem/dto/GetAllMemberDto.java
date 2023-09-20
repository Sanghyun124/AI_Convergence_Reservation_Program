package CSS.ReservationSystem.dto;

import CSS.ReservationSystem.domain.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMemberDto {

    private Long id;

    private Integer studentId;

    private String name;

    private Role role;

    private String email;

}
