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

        Cart addCart = cartRepository.findByMemberId(memberId);

        for (int i = 0; i < addCart.getItemIdList().size(); i++) {
            if (addCart.getItemIdList().get(i).getItemId().equals(cartItemDto.getItemId())) {
                addCart.getItemIdList().get(i).addOrderQuantity(cartItemDto.getOrderQuantity());
                return;
            }
        }

        CartItem addCartItem = cartItemDto.cartItemDtoToEntity();
        addCart.addCartItem(addCartItem);

    }

    public void removeCartItem(Long memberId, CartItemDto cartItemDto) {

        Cart removeCart = cartRepository.findByMemberId(memberId);

        for (int i = 0; i < removeCart.getItemIdList().size(); i++) {
            if (removeCart.getItemIdList().get(i).getItemId().equals(cartItemDto.getItemId())) {
                removeCart.getItemIdList().remove(i);
                return;
            }
        }

    }

}
