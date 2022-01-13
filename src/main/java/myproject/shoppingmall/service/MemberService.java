package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Address;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.form.JoinForm;
import myproject.shoppingmall.domain.form.LoginForm;
import myproject.shoppingmall.repository.MemberRepository;
import org.springframework.stereotype.Service;

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

    //회원가입
    public Long join(JoinForm joinForm) {

        validateDuplicateLoginId(joinForm.getLoginId()); // 중복 회원 검증 로직

        Member member = memberRepository.save(DtoToEntity(joinForm));

        return member.getId();
    }

    // 중복 회원 검증 로직
    private void validateDuplicateLoginId(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member DtoToEntity(JoinForm joinForm) {

        Member member = new Member();
        member.setName(joinForm.getName());
        member.setLoginId(joinForm.getLoginId());
        member.setPassword(joinForm.getPassword());
        member.setAddress(new Address(joinForm.getCity(), joinForm.getStreet(), joinForm.getZipcode()));

        return member;

    }

}
