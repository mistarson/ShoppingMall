package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
