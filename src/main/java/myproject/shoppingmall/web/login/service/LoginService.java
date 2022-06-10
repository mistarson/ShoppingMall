package myproject.shoppingmall.web.login.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.domain.member.service.MemberService;
import myproject.shoppingmall.global.error.exception.BusinessException;
import myproject.shoppingmall.global.error.exception.ErrorCode;
import myproject.shoppingmall.web.login.form.RegisterMemberForm;
import myproject.shoppingmall.web.login.form.UpdateMemberForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public Long registerMember(RegisterMemberForm registerMemberForm) {

        validateDuplicateMember(registerMemberForm.getLoginId());

        Member member = registerMemberForm.toEntity(passwordEncoder);
        Member saveMember = Member.createMember(member);

        Member savedMember = memberService.registerMember(saveMember);

        return savedMember.getId();
    }

    @Transactional
    public void updateMember(UpdateMemberForm updateMemberForm) throws BusinessException {

        Member member = memberService.findByLoginId(updateMemberForm.getLoginId()).orElseThrow(() -> new BusinessException(ErrorCode.NON_EXISTENT_MEMBER));
        Member updateMember = updateMemberForm.toEntity();

        member.updateMember(updateMember);
    }

    private void validateDuplicateMember(String loginId) {
        Optional<Member> optionalMember = memberService.findByLoginId(loginId);
        if (optionalMember.isPresent()) {
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }
    }
}
