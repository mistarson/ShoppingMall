package myproject.shoppingmall.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;
import myproject.shoppingmall.domain.order.entity.Order;
import myproject.shoppingmall.domain.order.constant.OrderStatus;

import java.time.LocalDateTime;

@Getter @ToString
public class OrderDto {
    private Long orderId;
    private LocalDateTime createDate;
    private String mainOrderItemImagePath;
    private String mainOrderItemName;
    private OrderStatus orderStatus;
    private int totalPrice;

    @QueryProjection
    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.createDate = order.getCreateDate();
        mainOrderItemImagePath = order.getOrderItemList()
                .get(0).getItem()
                .getImageList()
                .get(0)
                .getImagePath();
        mainOrderItemName = order.getOrderItemList()
                .get(0).getItem()
                .getName();
        this.orderStatus = order.getStatus();
        this.totalPrice = order.getTotalPrice();
    }
}
