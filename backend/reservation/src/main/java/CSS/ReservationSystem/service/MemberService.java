package CSS.ReservationSystem.service;

import CSS.ReservationSystem.dto.GetUserDto;
import CSS.ReservationSystem.dto.LoginRequestDto;
import CSS.ReservationSystem.dto.LoginResponseDto;

public interface MemberService {

    public GetUserDto getUserNameById(Long id);

    public LoginResponseDto login(LoginRequestDto request) throws Exception;
}
