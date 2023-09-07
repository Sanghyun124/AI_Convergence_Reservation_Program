package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.dto.GetReservationDto;
import CSS.ReservationSystem.repository.ReservationRepository;
import CSS.ReservationSystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            newDto.setReservationId(reservation.getId());
            newDto.setDate(reservation.getDate());
            newDto.setStartTime(reservation.getStartTime());
            newDto.setEndTime(reservation.getEndTime());
            newDto.setRoomId(reservation.getRoom().getId());
            newDto.setMemberId(reservation.getMember().getId());
            return newDto;
        }).collect(Collectors.toList());

    }

    public List<GetReservationDto> getReservationByDate(int date){
        int year = 2000+(date/10000);
        int month = (date/100)%100;
        int day = (date%100);
        LocalDate target = LocalDate.of(year,month,day);
        List<Reservation> reservations = reservationRepository.findAllByDate(target);
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

    public void deleteReservation(Long id){
        reservationRepository.deleteById(id);
    }

}
