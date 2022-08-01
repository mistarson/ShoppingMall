package myproject.shoppingmall.domain.cart.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.domain.cartItem.entity.CartItem;
import myproject.shoppingmall.web.form.ModifyOrderQuantityForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", unique = true, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItemList = new ArrayList<>();

    @Builder
    public Cart(Member member) {
        this.member = member;
    }

    public void modifyOrderQuantity(ModifyOrderQuantityForm modifyOrderQuantityForm) {
        for (CartItem cartItem : cartItemList) {
            if (isSameCartItem(cartItem.getItemId(), modifyOrderQuantityForm.getItemId())) {
                cartItem.setOrderQuantity(modifyOrderQuantityForm.getOrderQuantity());
            }
        }
    }

    public void removeCartItem(Long itemId) {
        for (CartItem cartItem : cartItemList) {
            if (isSameCartItem(cartItem.getItemId(), itemId)) {
                cartItemList.remove(cartItem);
                return;
            }
        }
    }

    //== 연관관계 편의 메서드==//
    public boolean addCartItem(CartItem addCartItem, int stockQuantity) {

        // 현재 장바구니에 추가한 상품과 같은 상품이 있는지 확인
        for (CartItem cartItem : cartItemList) {
            if (isSameCartItem(cartItem.getItemId(), addCartItem.getItemId())) {
                if (cartItem.getOrderQuantity() + addCartItem.getOrderQuantity() > stockQuantity) {
                    return false;
                }
                cartItem.addOrderQuantity(addCartItem.getOrderQuantity());
                return true;
            }
        }

        // 같은 상품이 없다면 장바구니에 상품을 추가
        cartItemList.add(addCartItem);
        addCartItem.setCart(this);

        return true;
    }

    private boolean isSameCartItem(Long originCartItemId, Long newCartItemId) {
        return originCartItemId.equals(newCartItemId);
    }

    public static Cart createCart(Member member) {
        return Cart.builder()
                .member(member)
                .build();
    }
}
