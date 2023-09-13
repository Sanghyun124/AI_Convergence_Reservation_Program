package CSS.ReservationSystem.dto;

import CSS.ReservationSystem.domain.Room;
import lombok.*;

import java.time.LocalTime;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {
    private Long roomId;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate date;

    private Long memberId;


}
