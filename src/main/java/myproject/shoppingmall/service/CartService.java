package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.cart.Cart;
import myproject.shoppingmall.domain.cart.CartItem;
import myproject.shoppingmall.dto.CartItemDto;
import myproject.shoppingmall.dto.MemberDto;
import myproject.shoppingmall.form.AddCartItemForm;
import myproject.shoppingmall.form.ModifyOrderQuantityForm;
import myproject.shoppingmall.repository.CartItemRepository;
import myproject.shoppingmall.repository.CartRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void addCartItem(AddCartItemForm addCartItemForm) throws Exception {

        MemberDto memberDto = memberService.getLoginMember();

        Cart findCart = cartRepository.findByMemberId(memberDto.getId());

        if (findCart == null) {
            Member member = memberService.findById(memberDto.getId());
            findCart = Cart.builder().member(member).build();
            cartRepository.save(findCart);
        }

        CartItem addCartItem = addCartItemForm.formToEntity();
        findCart.addCartItem(addCartItem);
    }

    @Transactional
    public void modifyOrderQuantity(ModifyOrderQuantityForm modifyOrderQuantityForm) throws Exception {

        MemberDto memberDto = memberService.getLoginMember();

        Cart findCart = cartRepository.findByMemberId(memberDto.getId());

        findCart.modifyOrderQuantity(modifyOrderQuantityForm);

    }

    @Transactional
    public void removeCartItem(Long itemId) throws Exception {

        MemberDto memberDto = memberService.getLoginMember();

        Cart findCart = cartRepository.findByMemberId(memberDto.getId());

        findCart.removeCartItem(itemId);

    }

    public Page<CartItemDto> findAllCartItem(Pageable pageable) throws Exception {

        MemberDto memberDto = memberService.getLoginMember();

        return cartItemRepository.findAllCartItem(memberDto.getId(), pageable);
    }


}
