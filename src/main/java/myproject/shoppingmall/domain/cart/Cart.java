package myproject.shoppingmall.domain.cart;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.form.ModifyOrderQuantityForm;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItemList = new ArrayList<>();

    @Builder
    public Cart(Member member) {
        this.member = member;
    }

    public void modifyOrderQuantity(ModifyOrderQuantityForm modifyOrderQuantityForm) {
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getItemId().equals(modifyOrderQuantityForm.getItemId())) {
                cartItemList.get(i).setOrderQuantity(modifyOrderQuantityForm.getOrderQuantity());
            }
        }
    }

    public void removeCartItem(Long itemId) {
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getItemId().equals(itemId)) {
                cartItemList.remove(i);
                return;
            }
        }
    }

    //== 연관관계 편의 메서드==//
    public boolean addCartItem(CartItem cartItem, int stockQuantity) {
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getItemId().equals(cartItem.getItemId())) {
                if (cartItemList.get(i).getOrderQuantity() + cartItem.getOrderQuantity() > stockQuantity) {
                    return false;
                }
                cartItemList.get(i).addOrderQuantity(cartItem.getOrderQuantity());
                return true;
            }
        }
        cartItemList.add(cartItem);
        cartItem.setCart(this);

        return true;
    }
}
