package myproject.shoppingmall.web.order.search;

import lombok.Getter;
import lombok.Setter;
import myproject.shoppingmall.domain.order.constant.OrderStatus;

@Getter @Setter
public class OrderSearch {
    private OrderSorter orderSorter;
    private OrderStatus orderStatus;
}
