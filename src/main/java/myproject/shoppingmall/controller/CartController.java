package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.dto.CartItemDto;
import myproject.shoppingmall.form.AddCartItemForm;
import myproject.shoppingmall.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/carts")
    public String showCart(@Login Member member, Model model) {

        List<CartItemDto> cartItems = cartService.findAllCartItem(member.getId());

        model.addAttribute("cartItems", cartItems);

        return "/shop/myCart";

    }

    @PostMapping("/carts")
    public String addCartItem(@Login Member member, AddCartItemForm addCartItemForm) throws Exception {

        cartService.addCartItem(member.getId(), addCartItemForm);

        return "redirect:/carts";
    }


}
