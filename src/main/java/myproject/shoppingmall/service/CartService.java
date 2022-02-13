package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.cart.Cart;
import myproject.shoppingmall.domain.cart.CartItem;
import myproject.shoppingmall.dto.CartItemDto;
import myproject.shoppingmall.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public void addCartItem(Long memberId, CartItemDto cartItemDto) {

        Cart cart = cartRepository.findByMemberId(memberId);
        CartItem addCartItem = cartItemDto.cartItemDtoToEntity();

        for (int i = 0; i < cart.getItemIdList().size(); i++) {
            if (cart.getItemIdList().get(i).getItemId().equals(addCartItem.getItemId())) {
                cart.getItemIdList().get(i).addOrderQuantity(addCartItem.getOrderQuantity());
                return;
            }
        }

        cart.addCartItem(addCartItem);

    }

    public void removeCartItem(Long memberId, CartItemDto cartItemDto) {

    }

}
