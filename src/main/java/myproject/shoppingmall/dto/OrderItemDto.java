package myproject.shoppingmall.dto;

import lombok.Getter;

@Getter
public class OrderItemDto {

    private String itemName;
    private int itemPrice;
    private int orderQuantity;
    private int totalPrice;

}
