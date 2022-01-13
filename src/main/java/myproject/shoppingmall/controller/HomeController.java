package myproject.shoppingmall.controller;

import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLoginWithSpringV5ArgumentResolver(@Login Member loginMember,
                                                        Model model) throws Exception {

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        /**
         * 로 그 인 된 화 면 으 로 바 꾸 기
         */
        //세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("member", loginMember);
        return "home"; // 로 그 인 된 화 면 으 로 바 꾸 기
    }
}
