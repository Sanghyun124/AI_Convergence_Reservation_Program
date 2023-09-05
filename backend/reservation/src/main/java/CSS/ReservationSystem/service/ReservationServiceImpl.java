package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.dto.GetReservationDto;
import CSS.ReservationSystem.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    public List<GetReservationDto> getReservation(){
        List<Reservation> reservations= reservationRepository.findAll();
        return reservations.stream().map(reservation ->{
            GetReservationDto newDto = new GetReservationDto();
            newDto.setDate(reservation.getDate());
            newDto.setStartTime(reservation.getStartTime());
            newDto.setEndTime(reservation.getEndTime());
            return newDto;
        }).collect(Collectors.toList());

    }


}
