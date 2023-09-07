package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.dto.GetUserDto;
import CSS.ReservationSystem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public GetUserDto getUserNameById(Long id) {
        Member member = memberRepository.findByid(id);

        GetUserDto newDto = new GetUserDto();
        newDto.setUserName(member.getName());

        return newDto;
    }
}
