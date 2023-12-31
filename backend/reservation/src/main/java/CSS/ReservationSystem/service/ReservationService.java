package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.dto.GetReservationDto;
import CSS.ReservationSystem.dto.ReservationRequestDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    public List<GetReservationDto> getReservation();

    public List<GetReservationDto> getReservationByDate(LocalDate date);

    public void deleteReservation(Long id);

    public List<GetReservationDto> getReservationByMember(Long id);

    public void createReservation(ReservationRequestDto reservationRequestDto) throws Exception;
}
