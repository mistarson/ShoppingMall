package myproject.shoppingmall.web.cart.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.web.dto.CartItemDto;
import myproject.shoppingmall.web.dto.ItemDto;
import myproject.shoppingmall.web.form.AddCartItemForm;
import myproject.shoppingmall.web.form.ModifyOrderQuantityForm;
import myproject.shoppingmall.global.security.AccountContext;
import myproject.shoppingmall.web.cart.service.CartService;
import myproject.shoppingmall.web.item.serivce.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;

    @GetMapping("/carts")
    public String showCart(Model model, @PageableDefault(size = 20) Pageable pageable) throws Exception {

        Page<CartItemDto> results = cartService.findAllCartItem(pageable);

        model.addAttribute("cartItems", results.getContent());
        model.addAttribute("totalPage", results.getTotalPages());

        return "shop/myCart";

    }

    @PostMapping("/carts")
    public String addCartItem(AddCartItemForm addCartItemForm, Model model) throws Exception {

        ItemDto item = itemService.findItem(addCartItemForm.getItemId());

        if (!cartService.addCartItem(addCartItemForm, item.getStockQuantity())) {

            Map<String, String> erros = new HashMap<>();

            erros.put("orderQuantity", "재고보다 많은 상품을 주문할 수 없습니다.(장바구니 확인 요망)");

            model.addAttribute("item", item);
            model.addAttribute("errors", erros);

            return "shop/itemDetail";

        }

        return "redirect:/carts";
    }

    @PostMapping("/carts/modify")
    public String modifyOrderQuantity(ModifyOrderQuantityForm modifyOrderQuantityForm, @AuthenticationPrincipal AccountContext accountContext) throws Exception {


        cartService.modifyOrderQuantity(modifyOrderQuantityForm, accountContext.getMember());

        return "redirect:/carts";
    }

    @PostMapping("/carts/remove")
    public String removeCartItem(@RequestParam("itemId") Long itemId) throws Exception {

        cartService.removeCartItem(itemId);

        return "redirect:/carts";
    }
}