package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.dto.GetReservationDto;
import CSS.ReservationSystem.repository.ReservationRepository;
import CSS.ReservationSystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public List<GetReservationDto> getReservation(){
        List<Reservation> reservations= reservationRepository.findAll();
        return reservations.stream().map(reservation ->{
            GetReservationDto newDto = new GetReservationDto();
            newDto.setReservationId(reservation.getId());
            newDto.setDate(reservation.getDate());
            newDto.setStartTime(reservation.getStartTime());
            newDto.setEndTime(reservation.getEndTime());
            newDto.setRoomId(reservation.getRoom().getId());
            return newDto;
        }).collect(Collectors.toList());

    }


}
