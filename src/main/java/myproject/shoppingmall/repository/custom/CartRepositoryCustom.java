package myproject.shoppingmall.repository.custom;

import myproject.shoppingmall.domain.cart.Cart;

public interface CartRepositoryCustom {
    Cart findByMemberId(Long memberId);
}
