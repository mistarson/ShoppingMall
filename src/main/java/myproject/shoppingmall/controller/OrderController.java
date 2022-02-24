package myproject.shoppingmall.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.argumentresolver.Login;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.RequestOrderItem;
import myproject.shoppingmall.form.RequestOrderItems;
import myproject.shoppingmall.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public String createOrder(@Login Member member, @RequestParam String data) {

        List<Map<String, Object>> infos = new Gson().fromJson(data,
                new TypeToken<List<Map<String, Object>>>(){}.getType());

        for (Map<String, Object> info : infos) {
            System.out.println(info.get("itemId") + " : " + info.get("orderQuantity"));
        }

//        orderService.createOrder(member.getId(), orderItemIdList);



        return "redirect:/carts";
    }
}
