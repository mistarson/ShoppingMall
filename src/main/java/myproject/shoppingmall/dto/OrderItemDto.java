package myproject.shoppingmall.dto;

import lombok.Getter;
import myproject.shoppingmall.domain.order.OrderItem;

@Getter
public class OrderItemDto {

    private Long itemId;
    private String itemName;
    private String itemMainImage;
    private int itemPrice;
    private int orderQuantity;
    private int totalPrice;

    public OrderItemDto(OrderItem orderItem) {
        this.itemId = orderItem.getItem().getId();
        this.itemName = orderItem.getItem().getName();
        this.itemMainImage = orderItem.getItem().getImageList().get(0).getImagePath();
        this.itemPrice = orderItem.getItem().getPrice();
        this.orderQuantity = orderItem.getOrderQuantity();
        this.totalPrice = orderItem.getTotalPrice();
    }
}
