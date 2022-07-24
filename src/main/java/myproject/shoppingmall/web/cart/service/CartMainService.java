package myproject.shoppingmall.web.cart.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.cart.entity.Cart;
import myproject.shoppingmall.domain.cart.service.CartService;
import myproject.shoppingmall.domain.cartItem.entity.CartItem;
import myproject.shoppingmall.domain.cartItem.repository.CartItemRepository;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.item.service.ItemService;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.global.error.exception.ErrorCode;
import myproject.shoppingmall.global.error.exception.NotEnoughStockException;
import myproject.shoppingmall.web.dto.CartItemDto;
import myproject.shoppingmall.web.dto.MemberDto;
import myproject.shoppingmall.web.form.AddCartItemForm;
import myproject.shoppingmall.web.form.ModifyOrderQuantityForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartMainService {

    private final CartService cartService;
    private final CartItemRepository cartItemRepository;
    private final ItemService itemService;

    @Transactional
    public void addCartItem(AddCartItemForm addCartItemForm, Member member) throws Exception {

        Cart cart = cartService.findByMemberId(member);
        CartItem cartItem = addCartItemForm.toEntity();
        Item item = itemService.findById(addCartItemForm.getItemId());

        if (!cart.addCartItem(cartItem, item.getStockQuantity())) {
            throw new NotEnoughStockException(ErrorCode.REST_STOCK_NOT_EXISTS);
        }

    }

    @Transactional
    public void modifyOrderQuantity(ModifyOrderQuantityForm modifyOrderQuantityForm, Member member) throws Exception {

        Cart findCart = cartService.findByMemberId(member);

        findCart.modifyOrderQuantity(modifyOrderQuantityForm);
    }

    @Transactional
    public void removeCartItem(Long itemId, Member member) throws Exception {

        Cart findCart = cartService.findByMemberId(member);
        findCart.removeCartItem(itemId);
    }

    public Page<CartItemDto> findAllCartItem(Member member, Pageable pageable) throws Exception {

        return cartItemRepository.findAllCartItem(member.getId(), pageable);
    }


}
