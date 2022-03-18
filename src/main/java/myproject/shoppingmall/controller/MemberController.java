package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.JoinForm;
import myproject.shoppingmall.dto.MemberDto;
import myproject.shoppingmall.security.AccountContext;
import myproject.shoppingmall.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/member")
    public String myInfo(Model model) throws Exception {

        MemberDto memberDto = memberService.getLoginMember();

        model.addAttribute("member", memberDto);

        return "member/myInfo";
    }

    @PostMapping("/member")
    public String updateMember(@Login Member loginMember,
                               @ModelAttribute MemberDto memberDto,
                               HttpServletRequest request,
                               BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "member/myInfo";
        }

        memberService.updateMember(loginMember.getId(), memberDto);

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
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

        memberService.join(joinForm);

        return "redirect:/";
    }

}
