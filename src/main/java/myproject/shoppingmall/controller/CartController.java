package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.AddCartItemForm;
import myproject.shoppingmall.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/carts")
    public String showCart(@Login Member member, Model model) {

    }

    @PostMapping("/carts")
    public String addCartItem(@Login Member member, AddCartItemForm addCartItemForm) {

        cartService.addCartItem(member.getId(), addCartItemForm);

        return "redirect:/carts";
    }


}
