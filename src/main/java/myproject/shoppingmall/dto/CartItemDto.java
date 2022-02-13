package myproject.shoppingmall.dto;

import lombok.Getter;
import myproject.shoppingmall.domain.cart.CartItem;

@Getter
public class CartItemDto {
    private Long itemId;
    private int orderQuantity;

    public CartItemDto(Long itemId, int orderQuantity) {
        this.itemId = itemId;
        this.orderQuantity = orderQuantity;
    }

    public CartItem cartItemDtoToEntity() {
        return CartItem.builder()
                .itemId(itemId)
                .orderQuantity(orderQuantity)
                .build();
    }


}
