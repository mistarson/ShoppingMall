package myproject.shoppingmall.domain.orderItem.repository;

import myproject.shoppingmall.domain.orderItem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
