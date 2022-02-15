package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.cart.Cart;
import myproject.shoppingmall.domain.cart.CartItem;
import myproject.shoppingmall.dto.CartItemDto;
import myproject.shoppingmall.form.AddCartItemForm;
import myproject.shoppingmall.repository.CartItemRepository;
import myproject.shoppingmall.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public void addCartItem(Long memberId, AddCartItemForm addCartItemForm) {

        Cart addCart = cartRepository.findByMemberId(memberId);

        for (int i = 0; i < addCart.getItemIdList().size(); i++) {
            if (addCart.getItemIdList().get(i).getItemId().equals(addCartItemForm.getItemId())) {
                addCart.getItemIdList().get(i).addOrderQuantity(addCartItemForm.getOrderQuantity());
                return;
            }
        }

        CartItem addCartItem = addCartItemForm.formToEntity();
        addCart.addCartItem(addCartItem);

    }

//    public void removeCartItem(Long memberId, CartItemDto cartItemDto) {
//
//        Cart removeCart = cartRepository.findByMemberId(memberId);
//
//        for (int i = 0; i < removeCart.getItemIdList().size(); i++) {
//            if (removeCart.getItemIdList().get(i).getItemId().equals(cartItemDto.getItemId())) {
//                removeCart.getItemIdList().remove(i);
//                return;
//            }
//        }
//    }

//    public List<CartItemDto> findAllCartItem(Long memberId) {
//        cartItemRepository.findAllCartItem(memberId);
//    }

}
