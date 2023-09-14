package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.domain.MemberDetails;
import CSS.ReservationSystem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findBystudentId(Integer.valueOf(username)).orElseThrow(
                () -> new UsernameNotFoundException("Invalid Authentication.")
        );

        return new MemberDetails(member);
    }
}
