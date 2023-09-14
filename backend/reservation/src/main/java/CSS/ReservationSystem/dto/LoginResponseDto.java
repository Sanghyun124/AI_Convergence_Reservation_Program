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

    private Integer studentId;

    private String name;

    private Role role;

    private String email;

    private String token;

    public LoginResponseDto(Member member) {
        this.id = member.getId();
        this.studentId = member.getStudentId();
        this.name = member.getName();
        this.role = member.getRole();
        this.email = member.getEmail();
    }

}
