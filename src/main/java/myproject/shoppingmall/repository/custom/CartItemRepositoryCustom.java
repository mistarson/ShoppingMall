package myproject.shoppingmall.repository.custom;

import myproject.shoppingmall.domain.cart.CartItem;
import myproject.shoppingmall.dto.CartItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepositoryCustom {
    Page<CartItemDto> findAllCartItem(Long memberId, Pageable pageable);
}
