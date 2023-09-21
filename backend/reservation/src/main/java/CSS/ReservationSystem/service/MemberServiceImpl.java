package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.dto.*;
import CSS.ReservationSystem.repository.MemberRepository;
import CSS.ReservationSystem.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
                .token(jwtTokenProvider.createToken(String.valueOf(member.getStudentId()), roles))
                .build();
    }

    @Override
    public Boolean updatePw(UpdatePwRequestDto request, Long id) throws Exception {
        Member member = memberRepository.findByid(id);

        if(!passwordEncoder.matches(request.getCurrentPw(), member.getPassword())) {
            throw new BadCredentialsException("Current Password Mismatch");
        }

        if(!Objects.equals(request.getNewPw(), request.getConfirmNewPw())) {
            throw new BadCredentialsException("Password Does Not Same");
        }

        member.updatePassword(passwordEncoder.encode(request.getNewPw()));
        memberRepository.save(member);

        return true;
    }

    @Override
    public List<GetAllMemberDto> getAllMember() {
        List<Member> members = memberRepository.findAll();

        return members.stream().map(member -> {
            GetAllMemberDto newDto = new GetAllMemberDto();

            newDto.setId(member.getId());
            newDto.setStudentId(member.getStudentId());
            newDto.setName(member.getName());
            newDto.setRole(member.getRole());
            newDto.setEmail(member.getEmail());

            return newDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addMember(MemberRequestDto request) throws Exception {
         Member member = Member.builder()
                 .studentId(request.getStudentId())
                 .password(passwordEncoder.encode(request.getPassword()))
                 .name(request.getName())
                 .role(request.getRole())
                 .email(request.getEmail())
                 .build();

         memberRepository.save(member);
    }
}
