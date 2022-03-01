package myproject.shoppingmall.domain.order;

import lombok.Getter;

@Getter
public class OrderSearch {
    private OrderSorter orderSorter;
    private OrderStatus orderStatus;
}
