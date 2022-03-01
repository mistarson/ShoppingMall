package myproject.shoppingmall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import myproject.shoppingmall.domain.order.Order;
import myproject.shoppingmall.domain.order.OrderStatus;

import java.time.LocalDateTime;

@Getter
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
