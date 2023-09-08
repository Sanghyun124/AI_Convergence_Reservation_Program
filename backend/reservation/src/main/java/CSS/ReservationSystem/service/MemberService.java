package CSS.ReservationSystem.service;

import CSS.ReservationSystem.dto.GetUserDto;

public interface MemberService {

    public GetUserDto getUserNameById(Long id);
}
