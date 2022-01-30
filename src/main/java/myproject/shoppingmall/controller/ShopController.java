package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ShopController {

    @GetMapping("/shop")
    public String shopHome(Model model) {
        return "/shop/shopHome";
    }
}
