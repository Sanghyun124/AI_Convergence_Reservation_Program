package CSS.ReservationSystem.dto;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
public class CreateReservationDto {
    private Member member;

    private Room room;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate date;

    public Reservation toEntity(){
        return Reservation.builder()
                .member(member)
                .room(room)
                .startTime(startTime)
                .endTime(endTime)
                .date(date)
                .build();
    }
}
