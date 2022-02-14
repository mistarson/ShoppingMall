package myproject.shoppingmall.form;

import lombok.Getter;
import myproject.shoppingmall.dto.CartItemDto;

@Getter
public class AddCartItemForm {
    private Long itemId;
    private int orderQuantity;

    public CartItemDto formToDto() {
        return new CartItemDto(itemId, orderQuantity);
    }
}
