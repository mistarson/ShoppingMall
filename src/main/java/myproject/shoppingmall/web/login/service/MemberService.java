package myproject.shoppingmall.web.login.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.member.constant.Address;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.web.dto.MemberDto;
import myproject.shoppingmall.web.member.form.UpdateMemberForm;
import myproject.shoppingmall.domain.member.repository.MemberRepository;
import myproject.shoppingmall.global.security.AccountContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }

    //TODO 조회는 모든 경우, S
    // 회원 전체 조회
    List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 로그인 아이디로 조회
    public Optional<Member> findByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }

    public Optional<Member> findByLoginIdForValidateDuplicate(String loginId){
        return memberRepository.findByLoginId(loginId);
    }

    // 멤버 아이디로 조회
    public Member findById(Long memberId) throws Exception {
        return memberRepository.findById(memberId).orElseThrow(() -> new Exception("해당 memberId를 가진 회원은 없습니다."));
    }



    @Transactional
    public void updateMember(UpdateMemberForm updateMemberForm) throws Exception {

        MemberDto loginMemberDto = getLoginMember();

        Member findMember = findById(loginMemberDto.getId());

        Address newAddress = new Address(updateMemberForm.getCity(), updateMemberForm.getStreet(), updateMemberForm.getZipcode());

        findMember.updateMember(updateMemberForm.getName(), newAddress);
    }

}
