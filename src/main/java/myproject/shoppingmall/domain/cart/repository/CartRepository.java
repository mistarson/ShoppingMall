package myproject.shoppingmall.domain.cart.repository;

import myproject.shoppingmall.domain.cart.entity.Cart;
import myproject.shoppingmall.domain.cart.repository.custom.CartRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {
}
