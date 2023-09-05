package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.dto.GetReservationDto;

import java.util.List;

public interface ReservationService {

    public List<GetReservationDto> getReservation();
}
