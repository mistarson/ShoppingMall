package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Address;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.form.JoinForm;
import myproject.shoppingmall.dto.MemberDto;
import myproject.shoppingmall.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 전체 조회
    List<Member> findMembers(){ return memberRepository.findAll();}

    // 로그인 아이디로 조회
    public Member findByLoginId(String loginId) throws Exception {
        return memberRepository.findByLoginId(loginId).orElseThrow(()-> new Exception("아이디에 해당하는 회원이 없습니다."));
    }

    // 멤버 아이디로 조회
    public Member findById(Long memberId) throws Exception {
        return memberRepository.findById(memberId).orElseThrow(()-> new Exception("해당 memberId를 가진 회원은 없습니다."));
    }


    private Member setMember(Long memberId) throws Exception {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new Exception("해당 memberId를 가진 회원은 없습니다."));

        return new Member(findMember.getLoginId(), findMember.getPassword(), findMember.getName(), findMember.getAddress());

    }

    //회원가입
    public Long join(JoinForm joinForm) {

        validateDuplicateLoginId(joinForm.getLoginId()); // 중복 회원 검증 로직

        Member member = memberRepository.save(joinFormToEntity(joinForm));

        return member.getId();
    }

    // 중복 회원 검증 로직
    private void validateDuplicateLoginId(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member joinFormToEntity(JoinForm joinForm) {

        Member member = new Member();
        member.setName(joinForm.getName());
        member.setLoginId(joinForm.getLoginId());
        member.setPassword(joinForm.getPassword());
        member.setAddress(new Address(joinForm.getCity(), joinForm.getStreet(), joinForm.getZipcode()));

        return member;

    }

    public MemberDto EntityToDto(Member loginMember) {

        MemberDto memberDto = new MemberDto();
        memberDto.setLoginId(loginMember.getLoginId());
        memberDto.setPassword(loginMember.getPassword());
        memberDto.setName(loginMember.getName());
        memberDto.setCity(loginMember.getAddress().getCity());
        memberDto.setStreet(loginMember.getAddress().getStreet());
        memberDto.setZipcode(loginMember.getAddress().getZipcode());

        return memberDto;
    }

    @Transactional
    public Member updateMember(Long id, MemberDto memberDto) throws Exception {

        Member findMember = findById(id);

        findMember.setLoginId(memberDto.getLoginId());
        findMember.setPassword(memberDto.getPassword());
        findMember.setName(memberDto.getName());
        findMember.setAddress(new Address(memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode()));

        return findMember;
    }
}
