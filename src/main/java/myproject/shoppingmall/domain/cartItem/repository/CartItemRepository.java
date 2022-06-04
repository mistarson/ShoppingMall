package myproject.shoppingmall.domain.cartItem.repository;

import myproject.shoppingmall.domain.cartItem.entity.CartItem;
import myproject.shoppingmall.domain.cartItem.repository.custom.CartItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long>, CartItemRepositoryCustom {
}
