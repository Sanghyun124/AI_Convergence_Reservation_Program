package CSS.ReservationSystem.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class findPwResponseDto {

    private String receiveAddress;

    private String mailTitle;

    private String mailContent;

}
