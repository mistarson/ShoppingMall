package myproject.shoppingmall.controller;

import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() throws Exception {
        return "home";
    }
}
