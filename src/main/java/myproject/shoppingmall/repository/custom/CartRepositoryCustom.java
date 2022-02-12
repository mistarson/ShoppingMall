package myproject.shoppingmall.repository.custom;

import myproject.shoppingmall.domain.cart.Cart;

import java.util.List;

public interface CartRepositoryCustom {
    List<Cart> findByMemberId(Long memberId);
}
