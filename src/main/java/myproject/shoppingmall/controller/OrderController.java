package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.RequestOrderItem;
import myproject.shoppingmall.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public String createOrder(@Login Member member, RequestOrderItem requestOrderItem) {

        System.out.println(requestOrderItem);

//        orderService.createOrder(member.getId());


        return "redirect:/carts";
    }
}
