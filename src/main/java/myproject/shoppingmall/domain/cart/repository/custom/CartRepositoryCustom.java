package myproject.shoppingmall.domain.cart.repository.custom;

import myproject.shoppingmall.domain.cart.entity.Cart;

public interface CartRepositoryCustom {
    Cart findByMemberId(Long memberId);
}
