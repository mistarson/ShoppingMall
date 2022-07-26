package myproject.shoppingmall.domain.cartItem.repository.custom;

import myproject.shoppingmall.web.dto.CartItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartItemRepositoryCustom {
    Page<CartItemDto> findAllCartItemForUser(Long memberId, Pageable pageable);
}
