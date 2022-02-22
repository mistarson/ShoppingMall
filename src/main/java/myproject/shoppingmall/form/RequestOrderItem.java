package myproject.shoppingmall.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestOrderItem {
    private Long itemId;
    private int orderQuantity;
}
