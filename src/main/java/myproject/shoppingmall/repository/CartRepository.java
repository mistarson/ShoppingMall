package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.cart.Cart;
import myproject.shoppingmall.repository.custom.CartRepositoryCustom;
import myproject.shoppingmall.repository.custom.CartRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {
}
