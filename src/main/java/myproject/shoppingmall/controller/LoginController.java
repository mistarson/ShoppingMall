package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.LoginForm;
import myproject.shoppingmall.service.LoginService;
import myproject.shoppingmall.session.SessionConst;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute LoginForm loginForm,
//                          BindingResult bindingResult,
//                          HttpServletResponse response,
//                          HttpServletRequest request,
//                          @RequestParam(defaultValue = "/") String redirectURL) throws Exception {
//
//        if (bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }
//
//        Member loginMember = loginService.login(loginForm);
//
//        if (loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
//            return "login/loginForm";
//        }
//
//        HttpSession session = request.getSession(); //세션이 있으면 있는 세션을 반환하고, 없으면 신규 세션을 생성해서 반환
//        /**
//         * getSession(default: true)
//         * true  : 세션이 있으면 기존 세션을 반환, 없으면 새로 생성
//         * false : 세션이 있으면 기존 세션을 반환, 없으면 null 반환 (신규 session X)
//         */
//        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember); //세션에 로그인 회원 정보를 보관
//
//        return "redirect:" + redirectURL;
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }

        return "redirect:/";

    }

//    private void expireCookie(HttpServletResponse response, String cookieName) {
//        Cookie cookie = new Cookie(cookieName, null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }
}
