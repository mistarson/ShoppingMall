package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.cart.Cart;
import myproject.shoppingmall.domain.cart.CartItem;
import myproject.shoppingmall.dto.CartItemDto;
import myproject.shoppingmall.form.AddCartItemForm;
import myproject.shoppingmall.form.ModifyOrderQuantityForm;
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
    private final MemberService memberService;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public void addCartItem(Long memberId, AddCartItemForm addCartItemForm) throws Exception {

        Cart findCart = cartRepository.findByMemberId(memberId);

        if (findCart == null) {
            Member member = memberService.findById(memberId);
            findCart = Cart.builder().member(member).build();
            cartRepository.save(findCart);
        }

        CartItem addCartItem = addCartItemForm.formToEntity();
        findCart.addCartItem(addCartItem);
    }

    @Transactional
    public void modifyOrderQuantity(Long memberId, ModifyOrderQuantityForm modifyOrderQuantityForm) {

        Cart findCart = cartRepository.findByMemberId(memberId);

        findCart.modifyOrderQuantity(modifyOrderQuantityForm);

    }

    @Transactional
    public void removeCartItem(Long memberId, Long itemId) {

        Cart findCart = cartRepository.findByMemberId(memberId);

        findCart.removeCartItem(itemId);

    }

    public List<CartItemDto> findAllCartItem(Long memberId) {
        return cartItemRepository.findAllCartItem(memberId);
    }


}
