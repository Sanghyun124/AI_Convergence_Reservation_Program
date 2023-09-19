package CSS.ReservationSystem.service;

import CSS.ReservationSystem.dto.*;

import java.util.List;

public interface MemberService {

    public GetUserDto getUserNameById(Long id) throws Exception;

    public LoginResponseDto login(LoginRequestDto request) throws Exception;

    public Boolean updatePw(UpdatePwRequestDto request, Long id) throws Exception;

    public List<GetAllMemberDto> getAllMember() throws Exception;
}
