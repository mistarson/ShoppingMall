package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.dto.MemberDto;
import myproject.shoppingmall.form.JoinForm;
import myproject.shoppingmall.form.UpdateMemberForm;
import myproject.shoppingmall.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String updateMember(UpdateMemberForm updateMemberForm) throws Exception {

        System.out.println(updateMemberForm);

        memberService.updateMember(updateMemberForm);

        return "redirect:/";
    }


    @GetMapping("/member/new")
    public String createForm(Model model) {

        model.addAttribute("joinForm", new JoinForm());
        return "member/createJoinForm";
    }

    @PostMapping("/member/new")
    public String create(@Valid JoinForm joinForm) {

        memberService.join(joinForm);

        return "redirect:/";
    }

}
