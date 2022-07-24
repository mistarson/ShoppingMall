package myproject.shoppingmall.web.cart.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.global.error.exception.NotEnoughStockException;
import myproject.shoppingmall.global.security.AccountContext;
import myproject.shoppingmall.web.cart.service.CartMainService;
import myproject.shoppingmall.web.dto.CartItemDto;
import myproject.shoppingmall.web.form.AddCartItemForm;
import myproject.shoppingmall.web.form.ModifyOrderQuantityForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CartMainController {

    private final CartMainService cartService;

    @GetMapping("/carts")
    public String showCart(Model model, @AuthenticationPrincipal AccountContext accountContext,
                           @PageableDefault(size = 20) Pageable pageable) throws Exception {

        Member member = accountContext.getMember();
        Page<CartItemDto> results = cartService.findAllCartItem(member, pageable);

        model.addAttribute("cartItems", results.getContent());
        model.addAttribute("totalPage", results.getTotalPages());

        return "shop/myCart";

    }

    @PostMapping("/carts")
    public String addCartItem(@Valid AddCartItemForm addCartItemForm, BindingResult bindingResult,
                              @AuthenticationPrincipal AccountContext accountContext, Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            return "shop/itemDetail";
        }

        try {
            cartService.addCartItem(addCartItemForm, accountContext.getMember());
        } catch (NotEnoughStockException e) {
            bindingResult.addError(new ObjectError("orderQuantity", e.getMessage()));

            return "shop/itemDetail";
        }

        return "redirect:/carts";
    }

    @PatchMapping("/carts")
    public String modifyOrderQuantity(ModifyOrderQuantityForm modifyOrderQuantityForm,
                                      @AuthenticationPrincipal AccountContext accountContext) throws Exception {

        cartService.modifyOrderQuantity(modifyOrderQuantityForm, accountContext.getMember());

        return "redirect:/carts";
    }

    @DeleteMapping("/carts/{item-id}")
    public String removeCartItem(@PathVariable("item-id") Long itemId,
                                 @AuthenticationPrincipal AccountContext accountContext) throws Exception {

        cartService.removeCartItem(itemId, accountContext.getMember());

        return "redirect:/carts";
    }
}
