package myproject.shoppingmall.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RequestOrderItems {
    List<RequestOrderItem> requestOrderItemList;
}
