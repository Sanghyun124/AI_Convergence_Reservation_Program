package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.domain.Room;
import CSS.ReservationSystem.dto.CreateReservationDto;
import CSS.ReservationSystem.dto.GetReservationDto;
import CSS.ReservationSystem.dto.ReservationRequestDto;
import CSS.ReservationSystem.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
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
            newDto.setMemberId(reservation.getMember().getId());
            return newDto;
        }).collect(Collectors.toList());

    }

    public List<GetReservationDto> getReservationByDate(LocalDate date){
        List<Reservation> reservations = reservationRepository.findAllByDate(date);
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

    public List<GetReservationDto> getReservationByMember(Long id){
        Member member = memberRepository.findByid(id);
        List<Reservation> reservations= reservationRepository.findAllByMember(member);
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

    public void createReservation(ReservationRequestDto reservationRequestDto) throws Exception {
        if(reservationRequestDto.getDate().getMonthValue()==LocalDate.now().getMonthValue()
                || reservationRequestDto.getDate().getMonthValue()==(LocalDate.now().getMonthValue()+1)){}
        else{
            throw new Exception("Check the month of your Reservation!");
        }

        Member member = memberRepository.findByid(reservationRequestDto.getMemberId());
        Room room =  roomRepository.findByid(reservationRequestDto.getRoomId());
        CreateReservationDto createReservationDto = new CreateReservationDto(
                member,
                room,
                reservationRequestDto.getStartTime(),
                reservationRequestDto.getEndTime(),
                reservationRequestDto.getDate()
        );

        List<Reservation> reservs = reservationRepository.findAllByDateAndRoom(createReservationDto.getDate(),createReservationDto.getRoom());

        if(reservs.isEmpty()){
            reservationRepository.save(createReservationDto.toEntity());
            return;
        }
        else{
            for(int i=0;i<reservs.size();i+=1){
                if(reservs.get(i).getStartTime().isBefore(createReservationDto.getStartTime())){
                    if(reservs.get(i).getEndTime().isAfter(createReservationDto.getStartTime())){
                        throw new Exception("Booked!!!!");
                    }
                }
                else {
                    if(reservs.get(i).getStartTime().isBefore(createReservationDto.getEndTime())) {
                        throw new Exception("Booked!!!!");
                    }
                }
            }

        reservationRepository.save(createReservationDto.toEntity());
        return;
        }
    }



}
