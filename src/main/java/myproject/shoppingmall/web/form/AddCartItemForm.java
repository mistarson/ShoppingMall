package myproject.shoppingmall.web.form;

import lombok.Getter;
import lombok.Setter;
import myproject.shoppingmall.domain.cartItem.entity.CartItem;

@Getter
@Setter
public class AddCartItemForm {
    private Long itemId;
    private int orderQuantity;

    public CartItem formToEntity() {
        return CartItem.builder().itemId(itemId).orderQuantity(orderQuantity).build();

    }
}
