package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.order.Order;
import myproject.shoppingmall.repository.custom.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
