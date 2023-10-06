package CSS.ReservationSystem.service;

import CSS.ReservationSystem.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    public GetUserDto getUserNameById(Long id);

    public LoginResponseDto login(LoginRequestDto request) throws Exception;

    public Boolean updatePw(UpdatePwRequestDto request, Long id) throws Exception;

    public List<GetAllMemberDto> getAllMember();

    public void addMember(MemberRequestDto request) throws Exception;

    public void updateMember(MemberRequestDto request, Long id) throws Exception;

    public void deleteMember(Long id) throws Exception;

    public List<Integer> registerMemberList(List<MemberRequestDto> requests) throws Exception;
}
