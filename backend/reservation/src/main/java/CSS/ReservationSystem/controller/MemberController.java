package CSS.ReservationSystem.controller;

import CSS.ReservationSystem.dto.GetUserDto;
import CSS.ReservationSystem.dto.LoginRequestDto;
import CSS.ReservationSystem.dto.LoginResponseDto;
import CSS.ReservationSystem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}") // id : member-id
    private ResponseEntity<GetUserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.getUserNameById(id));
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> loginRequest(@RequestBody LoginRequestDto request) throws Exception {
        return ResponseEntity.ok().body(memberService.login(request));
    }
}
