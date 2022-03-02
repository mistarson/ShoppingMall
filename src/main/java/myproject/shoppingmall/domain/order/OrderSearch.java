package myproject.shoppingmall.domain.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private OrderSorter orderSorter;
    private OrderStatus orderStatus;
}
