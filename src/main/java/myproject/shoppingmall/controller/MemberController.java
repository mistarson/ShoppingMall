package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.form.JoinForm;
import myproject.shoppingmall.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/member")
    public String myInfo(@Login Member loginMember, Model model) throws Exception {

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "redirect:/login";
        }

        model.addAttribute("member", loginMember);
        return "member/myInfo";
    }

    @GetMapping("/member/new")
    public String createForm(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "member/createJoinForm";
    }

    @PostMapping("/member/new")
    public String create(@Valid JoinForm joinForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/createJoinForm";
        }

        memberService.join(joinForm);

        return "redirect:/";
    }

    @GetMapping("/members/{memberId}")
    public String updateItemForm(@PathVariable("memberId") Long memberId, Model model) throws Exception {
        Member member = memberService.findMyInfo(memberId);

        model.addAttribute("member", member);

        return "member/myInfo";
    }
}
