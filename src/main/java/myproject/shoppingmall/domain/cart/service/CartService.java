package myproject.shoppingmall.domain.cart.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.cart.entity.Cart;
import myproject.shoppingmall.domain.cart.repository.CartRepository;
import myproject.shoppingmall.domain.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public Cart findByMemberId(Member member) {
        return cartRepository.findByMemberId(member.getId())
                .orElseGet(() -> {
                            Cart cart = Cart.createCart(member);
                            return cartRepository.save(cart);
                        }
                );

    }
}
