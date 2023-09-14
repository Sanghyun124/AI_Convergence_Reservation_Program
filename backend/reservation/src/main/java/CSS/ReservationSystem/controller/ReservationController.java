package CSS.ReservationSystem.controller;

import CSS.ReservationSystem.dto.GetReservationDto;
import CSS.ReservationSystem.dto.ReservationRequestDto;
import CSS.ReservationSystem.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @ApiOperation(value="예약정보", notes="전체 예약정보 가져오기")
    @GetMapping("")
    private ResponseEntity<List<GetReservationDto>> getReservation(){
        return ResponseEntity.ok().body(reservationService.getReservation());
    }

    @ApiOperation(value="해당 일의 예약정보", notes="해당 일의 예약정보 가져오기")
    @GetMapping("/date/{date}")
    private ResponseEntity<List<GetReservationDto>> getReservationByDate(@PathVariable LocalDate date){
        return ResponseEntity.ok().body(reservationService.getReservationByDate(date));
    }

    @ApiOperation(value="예약삭제", notes="해당 예약 삭제")
    @DeleteMapping("/{id}")//id : reservation-id
    private ResponseEntity<HttpStatus> deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value="해당 멤버의 예약정보", notes="해당 멤버의 예약정보 가져오기")
    @GetMapping("/member/{id}") // id : member-id
    private ResponseEntity<List<GetReservationDto>> getReservationByMember(@PathVariable Long id){
        return ResponseEntity.ok().body(reservationService.getReservationByMember(id));
    }

    @ApiOperation(value="예약하기", notes="신규 예약 하기")
    @PostMapping("/create")
    private ResponseEntity<HttpStatus> createReservation(@RequestBody ReservationRequestDto reservationRequestDto) throws Exception{
        reservationService.createReservation(reservationRequestDto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }
}
