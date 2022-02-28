package myproject.shoppingmall.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class DirectOrderItemForm {
    private String itemId;
    private String orderQuantity;

    public DirectOrderItemForm(String itemId, String orderQuantity) {
        this.itemId = itemId;
        this.orderQuantity = orderQuantity;
    }
}
