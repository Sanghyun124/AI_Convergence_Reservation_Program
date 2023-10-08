package CSS.ReservationSystem.controller;

import CSS.ReservationSystem.dto.*;
import CSS.ReservationSystem.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "로그인 정보", notes = "해당 멤버의 이름 가져오기")
    @GetMapping("/{id}") // id : member-id
    private ResponseEntity<GetUserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.getUserNameById(id));
    }

    @ApiOperation(value = "로그인 요청", notes = "로그인 요청")
    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> loginRequest(@RequestBody LoginRequestDto request) throws Exception {
        return ResponseEntity.ok().body(memberService.login(request));
    }

    @ApiOperation(value = "비밀번호 변경", notes = "해당 멤버의 비밀번호 변경")
    @PutMapping("/password/{id}")
    private ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePwRequestDto request, @PathVariable Long id) throws Exception {
        Boolean tf;
        
        try {
            tf = memberService.updatePw(request, id);
        } catch(Exception e) {
            e.printStackTrace();
            tf = false;
        }
        
        return ResponseEntity.ok().body(tf);
    }

    @ApiOperation(value = "비밀번호 찾기", notes = "임시 비밀번호 발급")
    @PostMapping("/password")
    private ResponseEntity<String> findPassword(@RequestBody findPwRequestDto request) throws Exception {
        String status;

        try {
            status = memberService.findPw(request);
        } catch(Exception e) {
            e.printStackTrace();
            status = e.getMessage();
        }

        return ResponseEntity.ok().body(status);
    }

    @ApiOperation(value = "멤버 정보", notes = "전체 멤버 정보 가져오기")
    @GetMapping("")
    private ResponseEntity<List<GetAllMemberDto>> getAllMember() {
        return ResponseEntity.ok().body(memberService.getAllMember());
    }

    @ApiOperation(value = "멤버 추가", notes = "신규 멤버 추가")
    @PostMapping("/add")
    private ResponseEntity<HttpStatus> addMember(@RequestBody MemberRequestDto request) throws Exception {
        memberService.addMember(request);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @ApiOperation(value = "멤버 정보 변경", notes = "해당 멤버의 멤버 정보 변경")
    @PutMapping("/{id}")
    private ResponseEntity<HttpStatus> updateMember(@RequestBody MemberRequestDto request, @PathVariable Long id) throws Exception {
        try {
            memberService.updateMember(request, id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "멤버 삭제", notes = "해당 멤버 삭제")
    @DeleteMapping("")
    private ResponseEntity<HttpStatus> deleteMember(@RequestBody List<Long> ids) throws Exception {
        memberService.deleteMember(ids);
        return ResponseEntity.ok().body(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "멤버 명단 일괄 등록", notes = "멤버 명단을 일괄적으로 등록, 중복된 학번 반환")
    @PostMapping("")
    private ResponseEntity<?> registerMemberList(@RequestBody List<MemberRequestDto> requests) throws Exception {
        return new ResponseEntity<>(memberService.registerMemberList(requests), HttpStatus.CREATED);
    }
}
