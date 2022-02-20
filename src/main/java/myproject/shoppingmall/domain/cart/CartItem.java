package myproject.shoppingmall.domain.cart;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private Long itemId;
    private int orderQuantity;

    @Builder
    public CartItem(Long itemId, int orderQuantity) {
        this.itemId = itemId;
        this.orderQuantity = orderQuantity;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void addOrderQuantity(int addQuantity) {
        this.orderQuantity += addQuantity;
    }
}
