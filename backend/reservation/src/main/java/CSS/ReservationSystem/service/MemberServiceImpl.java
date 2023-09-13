package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.dto.GetUserDto;
import CSS.ReservationSystem.dto.LoginRequestDto;
import CSS.ReservationSystem.dto.LoginResponseDto;
import CSS.ReservationSystem.repository.MemberRepository;
import CSS.ReservationSystem.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public GetUserDto getUserNameById(Long id) {
        Member member = memberRepository.findByid(id);

        GetUserDto newDto = new GetUserDto();
        newDto.setUserName(member.getName());

        return newDto;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) throws Exception {
        Member member = memberRepository.findBystudentId(request.getStudentId()).orElseThrow(() ->
                new BadCredentialsException("Invalid Account Information."));

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("Invalid Account Information.");
        }

        List<String> roles = new ArrayList<>();
        roles.add(member.getRole().value());

        return LoginResponseDto.builder()
                .id(member.getId())
                .studentId(member.getStudentId())
                .name(member.getName())
                .role(member.getRole())
                .email(member.getEmail())
                .token(jwtTokenProvider.createToken(member.getStudentId(), roles))
                .build();
    }
}
