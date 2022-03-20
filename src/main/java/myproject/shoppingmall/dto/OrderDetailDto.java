package myproject.shoppingmall.dto;

import com.querydsl.core.annotations.QueryProjection;
import myproject.shoppingmall.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailDto {
    private Long orderId;
    private LocalDateTime createDate;
    private List<OrderItemDto> orderItemList;
    private OrderStatus orderStatus;
    private int orderTotalPrice;

    @QueryProjection
    public OrderDetailDto() {

    }
}
