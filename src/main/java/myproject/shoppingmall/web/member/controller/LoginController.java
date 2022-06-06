package myproject.shoppingmall.web.member.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.global.error.exception.BusinessException;
import myproject.shoppingmall.global.security.AccountContext;
import myproject.shoppingmall.web.login.form.RegisterMemberForm;
import myproject.shoppingmall.web.login.service.LoginService;
import myproject.shoppingmall.web.member.form.UpdateMemberForm;
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
public class LoginController {

    private final LoginService loginService;
    
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
    public String registerMemberForm(Model model) {

        model.addAttribute("registerMemberForm", new RegisterMemberForm());

        return "/member/registerMemberForm";
    }

    @PostMapping("/member/new")
    public String registerMember(@Valid RegisterMemberForm registerMemberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/member/registerMemberForm";
        }

        try {
            loginService.registerMember(registerMemberForm);
        } catch (BusinessException e) {
            e.printStackTrace();
            bindingResult.addError(new ObjectError("registerMemberForm", e.getMessage()));
            return "/member/registerMemberForm";
        }

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
