package CSS.ReservationSystem.controller;

import CSS.ReservationSystem.dto.GetUserDto;
import CSS.ReservationSystem.dto.LoginRequestDto;
import CSS.ReservationSystem.dto.LoginResponseDto;
import CSS.ReservationSystem.dto.UpdatePwRequestDto;
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
    private ResponseEntity<GetUserDto> getUserById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(memberService.getUserNameById(id));
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> loginRequest(@RequestBody LoginRequestDto request) throws Exception {
        return ResponseEntity.ok().body(memberService.login(request));
    }

    @PutMapping("/password/{id}")
    private ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePwRequestDto request, @PathVariable Long id) throws Exception {
        Boolean tf;
        
        try {
            tf = memberService.updatePw(request, id);
        } catch (Exception e) {
            e.printStackTrace();
            tf = false;
        }
        
        return ResponseEntity.ok().body(tf);
    }
}
