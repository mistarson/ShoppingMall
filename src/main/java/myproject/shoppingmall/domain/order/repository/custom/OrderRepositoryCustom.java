package myproject.shoppingmall.domain.order.repository.custom;

import myproject.shoppingmall.domain.order.entity.Order;
import myproject.shoppingmall.web.order.search.OrderSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {
    Page<Order> getMyOrderList(Long memberId, OrderSearch orderSearch, Pageable pageable);
    Order getOrderDetail(Long memberId, Long orderId);
}
