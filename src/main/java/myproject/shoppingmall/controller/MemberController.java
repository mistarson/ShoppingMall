package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.form.JoinForm;
import myproject.shoppingmall.dto.MemberDto;
import myproject.shoppingmall.service.MemberService;
import myproject.shoppingmall.session.SessionConst;
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

//TODO 아규먼트 리졸버를 사용하여 로그인 시, 꼭 엔티티를 사용해야 할까?
    @GetMapping("/member")
    public String myInfo(@Login Member loginMember, Model model) throws Exception {

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "redirect:/login";
        }
        MemberDto memberDto = memberService.EntityToDto(loginMember);

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

        if (bindingResult.hasErrors()) {
            return "member/createJoinForm";
        }

        memberService.join(joinForm);

        return "redirect:/";
    }

}
