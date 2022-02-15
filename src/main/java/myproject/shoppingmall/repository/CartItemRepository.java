package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.cart.CartItem;
import myproject.shoppingmall.repository.custom.CartItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long>, CartItemRepositoryCustom {
}
