package myproject.shoppingmall.domain.order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.aop.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member orderer;

    // TODO 연관관계의 주인을 MANY쪽에 둘거임(소스는 여기에 조인컬럼을 둠)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private int totalPrice;

    @Builder
    public Order(Member orderer, List<OrderItem> orderItemList, Delivery delivery) {
        this.orderer = orderer;
        this.orderItemList = orderItemList;
        this.delivery = delivery;
    }

    private void setTotalPrice() {
        this.totalPrice = this.orderItemList.stream()
                .mapToInt(OrderItem::getOrderQuantity)
                .sum();
    }



}
