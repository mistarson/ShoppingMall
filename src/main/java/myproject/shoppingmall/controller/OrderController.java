package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.item.ItemSearch;
import myproject.shoppingmall.domain.order.Order;
import myproject.shoppingmall.domain.order.OrderSearch;
import myproject.shoppingmall.dto.OrderDetailDto;
import myproject.shoppingmall.dto.OrderDto;
import myproject.shoppingmall.form.DirectOrderItem;
import myproject.shoppingmall.form.RequestOrderItem;
import myproject.shoppingmall.form.RequestOrderItems;
import myproject.shoppingmall.service.ItemService;
import myproject.shoppingmall.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute RequestOrderItems requestOrderItems) throws Exception {

        orderService.createOrder(requestOrderItems);

        return "redirect:/orders";
    }

    @PostMapping("/orders/direct")
    public String createDirectOrder(@ModelAttribute DirectOrderItem directOrderItem) throws Exception {

        orderService.createDirectOrder(directOrderItem);

        return "redirect:/orders";
    }

    @PostMapping("/orders/cancel")
    public String cancelOrder(Long orderId) {
        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String getMyOrderList(@ModelAttribute OrderSearch orderSearch, @PageableDefault(size = 10) Pageable pageable, Model model) throws Exception {

        Page<OrderDto> myOrderList = orderService.getMyOrderList(orderSearch, pageable);

        model.addAttribute("orders", myOrderList.getContent());
        model.addAttribute("totalPage", myOrderList.getTotalPages());

        return "order/myOrderList";
    }

    @GetMapping("/orders/{orderId}")
    public String getOrderDetail(@PathVariable("orderId") Long orderId, Model model) throws Exception {
        OrderDetailDto orderDetail = orderService.getOrderDetail(orderId);

        model.addAttribute("orderDetail", orderDetail);

        return "order/orderDetail";

    }
}
