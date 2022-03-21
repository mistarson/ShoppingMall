package myproject.shoppingmall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import myproject.shoppingmall.domain.order.OrderItem;
import myproject.shoppingmall.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderDetailDto {
    private Long orderId;
    private LocalDateTime createDate;
    private List<OrderItemDto> orderItemList;
    private OrderStatus orderStatus;
    private int orderTotalPrice;

    @QueryProjection
    public OrderDetailDto(Long orderId, LocalDateTime createDate, List<OrderItem> orderItemList, OrderStatus orderStatus, int orderTotalPrice) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.orderItemList = orderItemList.stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.orderStatus = orderStatus;
        this.orderTotalPrice = orderTotalPrice;

    }
}
