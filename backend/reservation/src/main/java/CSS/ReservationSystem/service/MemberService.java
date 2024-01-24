package CSS.ReservationSystem.service;

import CSS.ReservationSystem.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MemberService {

    public GetUserDto getUserNameById(Long id, HttpServletRequest request) throws Exception;

    public LoginResponseDto login(LoginRequestDto request) throws Exception;

    public Boolean updatePw(UpdatePwRequestDto request, Long id) throws Exception;

    public String findPw(findPwRequestDto request) throws Exception;

    public List<GetAllMemberDto> getAllMember();

    public void addMember(MemberRequestDto request) throws Exception;

    public void updateMember(MemberRequestDto request, Long id) throws Exception;

    public void deleteMember(List<Long> ids) throws Exception;

    public List<Integer> registerMemberList(List<MemberRequestDto> requests) throws Exception;
}
