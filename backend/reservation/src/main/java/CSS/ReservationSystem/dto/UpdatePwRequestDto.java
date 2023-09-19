package CSS.ReservationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePwRequestDto {

    private String currentPw;

    private String newPw;

    private String confirmNewPw;

}
