package myproject.shoppingmall.domain.cartItem.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.cartItem.repository.CartItemRepository;
import myproject.shoppingmall.web.dto.CartItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public Page<CartItemDto> findAllCartItemForUser(Long memberId, Pageable pageable) {
        return cartItemRepository.findAllCartItemForUser(memberId, pageable);
    }
}
