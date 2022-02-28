package myproject.shoppingmall.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.DirectOrderItemForm;
import myproject.shoppingmall.form.RequestOrderItem;
import myproject.shoppingmall.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public String createOrder(@Login Member member, @RequestParam String data) throws Exception {

        List<Map<String, Object>> orderItemList = new Gson().fromJson(data,
                new TypeToken<List<Map<String, Object>>>(){}.getType());

        List<RequestOrderItem> requestOrderItemList = new ArrayList<>();
        for (Map<String, Object> info : orderItemList) {
            requestOrderItemList.add(new RequestOrderItem(
                    Long.parseLong(String.valueOf(info.get("itemId"))), Integer.parseInt(String.valueOf(info.get("orderQuantity")))));

        }

        orderService.createOrder(member.getId(), requestOrderItemList);



        return "redirect:/carts";
    }

    @PostMapping("/orders/direct")
    public String createDirectOrder(@Login Member member, @RequestBody DirectOrderItemForm directOrderItemForm) throws Exception {

        System.out.println("requestOrderItem = " + directOrderItemForm.getItemId());
        System.out.println("requestOrderItem = " + directOrderItemForm.getOrderQuantity());
        orderService.createDirectOrder(member.getId(), directOrderItemForm);

        return "redirect:/carts";
    }
}
