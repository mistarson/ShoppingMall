package myproject.shoppingmall.domain.order;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.aop.BaseEntity;
import myproject.shoppingmall.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

    private int orderQuantity;
    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public OrderItem(Item item, int orderQuantity) {
        order(item, orderQuantity);
        totalPrice = item.getPrice() * orderQuantity;
        this.item = item;
        this.orderQuantity = orderQuantity;
    }

    private void order(Item item, int orderQuantity) {
        item.removeStock(orderQuantity);
    }

    //==비즈니스 로직==//
    public void cancel() {
        item.addStock(orderQuantity);
    }



}
