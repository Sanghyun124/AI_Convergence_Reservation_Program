package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.dto.GetReservationDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    public List<GetReservationDto> getReservation();

    public List<GetReservationDto> getReservationByDate(int date);


}
