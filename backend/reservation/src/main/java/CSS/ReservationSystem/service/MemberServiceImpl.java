package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.dto.*;
import CSS.ReservationSystem.repository.MemberRepository;
import CSS.ReservationSystem.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    @Override
    public void updateMember(MemberRequestDto request, Long id) throws Exception {
        Member member = memberRepository.findByid(id);

        if(!Objects.nonNull(request.getStudentId())) {
            throw new NullPointerException("Input Student ID is Null");
        }
        if(request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new NullPointerException("Input Password is Null");
        }
        if(request.getName() == null || request.getName().isEmpty()) {
            throw new NullPointerException("Input Name is Null");
        }
        if(request.getRole() == null) {
            throw new NullPointerException("Input Role is Null or Invalid Value");
        }
        if(request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new NullPointerException("Email is Null");
        }

        member.updateStudentId(request.getStudentId());
        member.updatePassword(passwordEncoder.encode(request.getPassword()));
        member.updateName(request.getName());
        member.updateRole(request.getRole());
        member.updateEmail(request.getEmail());

        memberRepository.save(member);
    }

    @Override
    public void deleteMember(List<Long> ids) throws Exception {
        for(Long id : ids) {
            memberRepository.deleteById(id);
        }
    }

    @Override
    public List<Integer> registerMemberList(List<MemberRequestDto> requests) {
        List<Integer> duplicatedList = new ArrayList<>();

        for (MemberRequestDto request : requests) {
            Optional<Member> duplicatedMember = memberRepository.findBystudentId(request.getStudentId());

            if (duplicatedMember.isEmpty()) {
                Member member = Member.builder()
                        .studentId(request.getStudentId())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .name(request.getName())
                        .role(request.getRole())
                        .email(request.getEmail())
                        .build();

                memberRepository.save(member);
            } else {
                duplicatedList.add(duplicatedMember.get().getStudentId());
            }
        }

        return duplicatedList;
    }
}
