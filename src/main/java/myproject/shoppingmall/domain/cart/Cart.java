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
    private List<CartItem> itemIdList = new ArrayList<>();

    @Builder
    public Cart(Member member) {
        this.member = member;
    }

    public void modifyOrderQuantity(ModifyOrderQuantityForm modifyOrderQuantityForm) {
        for (int i = 0; i < itemIdList.size(); i++) {
            if (itemIdList.get(i).getItemId().equals(modifyOrderQuantityForm.getItemId())) {
                itemIdList.get(i).setOrderQuantity(modifyOrderQuantityForm.getOrderQuantity());
            }
        }
    }

    //== 연관관계 편의 메서드==//
    public void addCartItem(CartItem cartItem) {
        itemIdList.add(cartItem);
        cartItem.setCart(this);
    }
}
