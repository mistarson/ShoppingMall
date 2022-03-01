package myproject.shoppingmall.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RequestOrderItems {
    private List<RequestOrderItem> orderItemList;
}
