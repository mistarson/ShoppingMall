package myproject.shoppingmall.web.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ModifyOrderQuantityForm {
    private Long itemId;
    private int orderQuantity;
}
