package myproject.shoppingmall.web.login.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.global.error.exception.BusinessException;
import myproject.shoppingmall.global.security.AccountContext;
import myproject.shoppingmall.web.login.form.RegisterMemberForm;
import myproject.shoppingmall.web.login.service.LoginService;
import myproject.shoppingmall.web.login.form.UpdateMemberForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {

        model.addAttribute("error", error);

        return "login/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }

        return "redirect:/";
    }
    
    @GetMapping("/member")
    public String myInfo(@AuthenticationPrincipal AccountContext accountContext, Model model) throws Exception {

        Member loginMember = accountContext.getMember();

        UpdateMemberForm updateMemberForm = UpdateMemberForm.from(loginMember);

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
            loginService.updateMember(updateMemberForm);
        } catch (BusinessException e) {
            e.printStackTrace();
            bindingResult.addError(new ObjectError("updateMemberForm", e.getMessage()));
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

}
