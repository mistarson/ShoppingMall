package myproject.shoppingmall.web.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RequestOrderItem {
    private Long itemId;
    private int orderQuantity;

    public RequestOrderItem(Long itemId, int orderQuantity) {
        this.itemId = itemId;
        this.orderQuantity = orderQuantity;
    }
}
