package myproject.shoppingmall.domain.order.repository;

import myproject.shoppingmall.domain.order.entity.Order;
import myproject.shoppingmall.domain.order.repository.custom.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
