package CSS.ReservationSystem.service;

import CSS.ReservationSystem.domain.Member;
import CSS.ReservationSystem.dto.*;
import CSS.ReservationSystem.repository.MemberRepository;
import CSS.ReservationSystem.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
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
    private final MailSender mailSender;

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
    public String findPw(findPwRequestDto request) throws Exception {
        // request validation
        Member member = memberRepository.findBystudentId(request.getStudentId()).orElseThrow(() ->
                new BadCredentialsException("Invalid Account Information."));

        if(!member.getEmail().equals(request.getEmail())) {
            throw new BadCredentialsException("Email Does Not Match");
        }

        // generate temporary password
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        StringBuilder tempPw = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int idx = (int) (charSet.length * Math.random());
            tempPw.append(charSet[idx]);
        }

        // set findPwResponseDto
        findPwResponseDto newDto = findPwResponseDto.builder()
                .receiveAddress(request.getEmail())
                .mailTitle("AI융합학부 세미나실 예약 시스템 임시 비밀번호 발급")
                .mailContent("안녕하세요.\nAI융합학부 세미나실 예약 시스템 임시 비밀번호 발급 관련 이메일입니다.\n\n회원님의 임시 비밀번호는 " +
                        tempPw + " 입니다.\n\n임시 비밀번호로 로그인 후 꼭 비밀번호 변경하시기 바랍니다.")
                .build();

        // send e-mail
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("changhoon.kim204@gmail.com");
        message.setTo(newDto.getReceiveAddress());
        message.setReplyTo("changhoon.kim204@gmail.com");
        message.setSubject(newDto.getMailTitle());
        message.setText(newDto.getMailContent());

        mailSender.send(message);

        // set a member's password as a temporary password
        member.updatePassword(passwordEncoder.encode(tempPw));
        memberRepository.save(member);

        return "Temporary password issued.";
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
