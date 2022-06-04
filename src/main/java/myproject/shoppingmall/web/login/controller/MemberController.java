package myproject.shoppingmall.web.login.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.web.form.JoinForm;
import myproject.shoppingmall.web.form.UpdateMemberForm;
import myproject.shoppingmall.global.security.AccountContext;
import myproject.shoppingmall.web.login.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    
    @GetMapping("/member")
    public String myInfo(@AuthenticationPrincipal AccountContext accountContext, Model model) throws Exception {

        Member loginMember = accountContext.getMember();

        UpdateMemberForm updateMemberForm = new UpdateMemberForm(loginMember);

        model.addAttribute("updateMemberForm", updateMemberForm);

        return "member/myInfo";
    }

    @PostMapping("/member")
    public String updateMember(@Valid UpdateMemberForm updateMemberForm,
                               BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "member/myInfo";
        }

        try {
            memberService.updateMember(updateMemberForm);
        } catch (Exception e) {
            e.printStackTrace();
            return "member/myInfo";
        }

        return "redirect:/";
    }


    @GetMapping("/member/new")
    public String createForm(Model model) {

        model.addAttribute("joinForm", new JoinForm());

        return "member/createJoinForm";
    }

    @PostMapping("/member/new")
    public String create(@Valid JoinForm joinForm, BindingResult bindingResult) {

        if (validateDuplicateLoginId(joinForm.getLoginId())) {
            bindingResult.addError(new ObjectError("joinForm", "중복된 아이디가 있습니다."));
        }

        if (bindingResult.hasErrors()) {
            return "member/createJoinForm";
        }

        memberService.join(joinForm);

        return "redirect:/";
    }

    // 중복 회원 검증 로직
    private boolean validateDuplicateLoginId(String loginId){
        Optional<Member> findMember = memberService.findByLoginIdForValidateDuplicate(loginId);
        if (findMember.isPresent()) {
            return true;
        }
        return false;
    }

}
