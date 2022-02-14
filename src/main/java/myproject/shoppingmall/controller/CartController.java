package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.AddCartItemForm;
import myproject.shoppingmall.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public String addCartItem(@Login Member member, @ModelAttribute @Valid AddCartItemForm addCartItemForm) {

        System.out.println("addCartItemForm = " + addCartItemForm.getItemId());
        System.out.println("addCartItemForm = " + addCartItemForm.getOrderQuantity());

        cartService.addCartItem(member.getId(), addCartItemForm.formToDto());

        return "redirect:/carts";

    }
}
