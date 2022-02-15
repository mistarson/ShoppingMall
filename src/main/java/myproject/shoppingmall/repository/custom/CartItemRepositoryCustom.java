package myproject.shoppingmall.repository.custom;

import myproject.shoppingmall.domain.cart.CartItem;
import myproject.shoppingmall.dto.CartItemDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepositoryCustom {
    List<CartItemDto> findAllCartItem(Long memberId);
}
