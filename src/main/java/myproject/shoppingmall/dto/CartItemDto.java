package myproject.shoppingmall.dto;

import lombok.Getter;

@Getter
public class CartItemDto {
    private Long itemId;
    private int orderQuantity;

    public CartItemDto(Long itemId, int orderQuantity) {
        this.itemId = itemId;
        this.orderQuantity = orderQuantity;
    }
}
