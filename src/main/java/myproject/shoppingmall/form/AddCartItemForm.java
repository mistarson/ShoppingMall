package myproject.shoppingmall.form;

import lombok.Getter;
import lombok.Setter;
import myproject.shoppingmall.domain.cart.CartItem;

@Getter
@Setter
public class AddCartItemForm {
    private Long itemId;
    private int orderQuantity;

    public CartItem formToEntity() {
        return CartItem.builder().itemId(itemId).orderQuantity(orderQuantity).build();

    }
}
