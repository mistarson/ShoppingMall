package myproject.shoppingmall.web.cart.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.domain.cart.entity.Cart;
import myproject.shoppingmall.domain.cartItem.entity.CartItem;
import myproject.shoppingmall.web.login.service.MemberService;
import myproject.shoppingmall.web.dto.CartItemDto;
import myproject.shoppingmall.web.dto.MemberDto;
import myproject.shoppingmall.web.form.AddCartItemForm;
import myproject.shoppingmall.web.form.ModifyOrderQuantityForm;
import myproject.shoppingmall.domain.cartItem.repository.CartItemRepository;
import myproject.shoppingmall.domain.cart.repository.CartRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final MemberService memberService;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public boolean addCartItem(AddCartItemForm addCartItemForm, int stockQuantity) throws Exception {

        MemberDto memberDto = memberService.getLoginMember();

        Cart findCart = cartRepository.findByMemberId(memberDto.getId());

        if (findCart == null) {
            Member member = memberService.findById(memberDto.getId());
            findCart = Cart.builder().member(member).build();
            cartRepository.save(findCart);
        }

        CartItem addCartItem = addCartItemForm.formToEntity();

        if (!findCart.addCartItem(addCartItem, stockQuantity)) {
            return false;
        }

        return true;
    }

    @Transactional
    public void modifyOrderQuantity(ModifyOrderQuantityForm modifyOrderQuantityForm, Member member) throws Exception {


        MemberDto memberDto = new MemberDto(member);

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
