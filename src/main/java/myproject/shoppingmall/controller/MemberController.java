package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.dto.MemberDto;
import myproject.shoppingmall.form.JoinForm;
import myproject.shoppingmall.form.UpdateMemberForm;
import myproject.shoppingmall.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        public String create(@Valid @ModelAttribute("joinForm") JoinForm joinForm, BindingResult bindingResult, Model model) throws Exception {

        if (validateDuplicateLoginId(joinForm.getLoginId())) {
            bindingResult.addError(new ObjectError("joinForm", "중복된 아이디가 존재합니다."));
        }

        if (bindingResult.hasErrors()) {
            return "member/createJoinForm";
        }

        memberService.join(joinForm);

        return "redirect:/";
    }

    // 중복 회원 검증 로직
    private boolean validateDuplicateLoginId(String loginId) throws Exception {
        Optional<Member> findMember = memberService.findByLoginIdForValidation(loginId);
        if (findMember.isPresent()) {
            return true;
        }
        return false;
    }

}
