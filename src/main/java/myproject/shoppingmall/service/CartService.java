package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.dto.CartItemDto;
import myproject.shoppingmall.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

   public void addCartItem(Long memberId ,CartItemDto cartItemDto) {

    }
}
