package myproject.shoppingmall.domain.order;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.auditing.BaseEntity;
import myproject.shoppingmall.domain.item.Item;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", unique = true, nullable = false)
    private Long id;

    private int orderQuantity;
    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public OrderItem(Item item, int orderQuantity) {
        this.item = item;
        this.orderQuantity = orderQuantity;
        order(item, orderQuantity);
        totalPrice = item.getPrice() * orderQuantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private void order(Item item, int orderQuantity) {
        item.removeStock(orderQuantity);
    }

    //==비즈니스 로직==//
    public void cancel() {
        item.addStock(orderQuantity);
    }



}
