package CSS.ReservationSystem.service;

import CSS.ReservationSystem.dto.GetUserDto;
import CSS.ReservationSystem.dto.LoginRequestDto;
import CSS.ReservationSystem.dto.LoginResponseDto;
import CSS.ReservationSystem.dto.UpdatePwRequestDto;

public interface MemberService {

    public GetUserDto getUserNameById(Long id) throws Exception;

    public LoginResponseDto login(LoginRequestDto request) throws Exception;

    public Boolean updatePw(UpdatePwRequestDto request, Long id) throws Exception;
}
