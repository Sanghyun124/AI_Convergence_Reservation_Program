package CSS.ReservationSystem.controller;

import CSS.ReservationSystem.domain.Reservation;
import CSS.ReservationSystem.dto.GetReservationDto;
import CSS.ReservationSystem.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
