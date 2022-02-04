package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.JoinForm;
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

    //TODO 조회는 모든 경우, S
    // 회원 전체 조회
    List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 로그인 아이디로 조회
    public Member findByLoginId(String loginId) throws Exception {
        return memberRepository.findByLoginId(loginId).orElseThrow(() -> new Exception("아이디에 해당하는 회원이 없습니다."));
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

        validateDuplicateLoginId(joinForm.getLoginId()); // 중복 회원 검증 로직

        Member member = memberRepository.save(joinForm.joinFormToEntity());

        return member.getId();
    }

    // 중복 회원 검증 로직
    private void validateDuplicateLoginId(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }



    @Transactional
    public void updateMember(Long id, MemberDto memberDto) throws Exception {

        Member findMember = findById(id);

        findMember.updateMember(memberDto.memberDtoToEntity());
    }
}
