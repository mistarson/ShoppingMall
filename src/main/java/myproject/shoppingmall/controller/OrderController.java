package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.order.OrderSearch;
import myproject.shoppingmall.form.DirectOrderItem;
import myproject.shoppingmall.form.RequestOrderItems;
import myproject.shoppingmall.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public String createOrder(@Login Member member, @ModelAttribute RequestOrderItems requestOrderItems) throws Exception {

        orderService.createOrder(member.getId(), requestOrderItems);

        return "redirect:/orders";
    }

    @PostMapping("/orders/direct")
    public String createDirectOrder(@Login Member member, @ModelAttribute DirectOrderItem directOrderItem) throws Exception {

        orderService.createDirectOrder(member.getId(), directOrderItem);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String getMyOrderList(@Login Member member, @ModelAttribute OrderSearch orderSearch, Pageable pageable, Model model) {

        orderService.getMyOrderList(member.getId(), orderSearch, pageable);

        return "order/myOrderList";
    }
}
