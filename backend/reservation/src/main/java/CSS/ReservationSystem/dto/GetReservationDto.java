package CSS.ReservationSystem.dto;

import CSS.ReservationSystem.domain.Room;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetReservationDto {

    private Long reservationId;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private Long roomId;
}
