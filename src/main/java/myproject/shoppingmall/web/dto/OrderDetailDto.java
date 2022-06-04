package myproject.shoppingmall.web.dto;

import lombok.Getter;
import myproject.shoppingmall.domain.order.entity.Order;
import myproject.shoppingmall.domain.order.constant.OrderStatus;

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

    public OrderDetailDto(Order order) {
        this.orderId = order.getId();
        this.createDate = order.getCreateDate();
        this.orderItemList = order.getOrderItemList().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.orderStatus = order.getStatus();
        this.orderTotalPrice = order.getTotalPrice();

    }
}
