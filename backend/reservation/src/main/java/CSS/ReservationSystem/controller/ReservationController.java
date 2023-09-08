package CSS.ReservationSystem.controller;

import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.dto.GetReservationDto;
import CSS.ReservationSystem.dto.ReservationRequestDto;
import CSS.ReservationSystem.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("")
    private ResponseEntity<List<GetReservationDto>> getReservation(){
        return ResponseEntity.ok().body(reservationService.getReservation());
    }

    @GetMapping("/date/{date}")
    private ResponseEntity<List<GetReservationDto>> getReservationByDate(@PathVariable int date){
        return ResponseEntity.ok().body(reservationService.getReservationByDate(date));
    }

    @DeleteMapping("/{id}")//id : reservation-id
    private ResponseEntity<HttpStatus> deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @GetMapping("/member/{id}") // id : member-id
    private ResponseEntity<List<GetReservationDto>> getReservationByMember(@PathVariable Long id){
        return ResponseEntity.ok().body(reservationService.getReservationByMember(id));
    }

    @PostMapping("/create")
    private ResponseEntity<HttpStatus> createReservation(@RequestBody ReservationRequestDto reservationRequestDto){
        reservationService.createReservation(reservationRequestDto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }
}
