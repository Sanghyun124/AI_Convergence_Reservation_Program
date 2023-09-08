package CSS.ReservationSystem.controller;

import CSS.ReservationSystem.dto.GetUserDto;
import CSS.ReservationSystem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}") // id : member-id
    private ResponseEntity<GetUserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.getUserNameById(id));
    }
}
