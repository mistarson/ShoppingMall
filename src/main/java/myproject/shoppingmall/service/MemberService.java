package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Address;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.JoinForm;
import myproject.shoppingmall.dto.MemberDto;
import myproject.shoppingmall.form.UpdateMemberForm;
import myproject.shoppingmall.repository.MemberRepository;
import myproject.shoppingmall.security.AccountContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //TODO 조회는 모든 경우, S
    // 회원 전체 조회
    List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 로그인 아이디로 조회
    public Member findByLoginId(String loginId) throws Exception {
        return memberRepository.findByLoginId(loginId).orElseThrow(() -> new Exception("아이디에 해당하는 회원이 없습니다."));
    }

    // 아이디 중복 확인을 위한
    public Optional<Member> findByLoginIdForValidation(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    // 멤버 아이디로 조회
    public Member findById(Long memberId) throws Exception {
        return memberRepository.findById(memberId).orElseThrow(() -> new Exception("해당 memberId를 가진 회원은 없습니다."));
    }


//    private Member setMember(Long memberId) throws Exception {
//        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new Exception("해당 memberId를 가진 회원은 없습니다."));
//
//        return new Member(findMember.getLoginId(), findMember.getPassword(), findMember.getName(), findMember.getAddress());
//
//    }

    //회원가입
    public Long join(JoinForm joinForm) {

        joinForm.setPassword(passwordEncoder.encode(joinForm.getPassword()));

        Member member = memberRepository.save(joinForm.joinFormToEntity());

        return member.getId();
    }

    @Transactional
    public void updateMember(UpdateMemberForm updateMemberForm) throws Exception {

        MemberDto loginMemberDto = getLoginMember();

        Member findMember = findById(loginMemberDto.getId());

        Address newAddress = new Address(updateMemberForm.getCity(), updateMemberForm.getStreet(), updateMemberForm.getZipcode());

        findMember.updateMember(updateMemberForm.getName(), newAddress);
    }

    //인증 회원 가져오기
    public MemberDto getLoginMember() throws Exception {
        AccountContext accountContext = (AccountContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member loginMember = findByLoginId(accountContext.getUsername());

        return new MemberDto(loginMember);

    }
}
