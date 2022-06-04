package myproject.shoppingmall.web.order.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.web.order.search.OrderSearch;
import myproject.shoppingmall.web.dto.OrderDetailDto;
import myproject.shoppingmall.web.dto.OrderDto;
import myproject.shoppingmall.web.form.DirectOrderItem;
import myproject.shoppingmall.web.form.RequestOrderItems;
import myproject.shoppingmall.web.order.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

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
