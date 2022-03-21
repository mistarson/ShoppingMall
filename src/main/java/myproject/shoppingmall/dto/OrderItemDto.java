package myproject.shoppingmall.dto;

import lombok.Getter;
import myproject.shoppingmall.domain.order.OrderItem;

@Getter
public class OrderItemDto {

    private String itemName;
    private int itemPrice;
    private int orderQuantity;
    private int totalPrice;

    public OrderItemDto(OrderItem orderItem) {
        this.itemName = orderItem.getItem().getName();
        this.itemPrice = orderItem.getItem().getPrice();
        this.orderQuantity = orderItem.getOrderQuantity();
        this.totalPrice = orderItem.getTotalPrice();
    }
}
