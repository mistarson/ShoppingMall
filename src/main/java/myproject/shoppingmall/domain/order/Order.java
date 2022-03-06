package myproject.shoppingmall.domain.order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.auditing.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private int totalPrice;

    @Builder
    public Order(Member member, Delivery delivery, List<OrderItem> orderItems) {
        this.delivery = delivery;
        this.status = OrderStatus.ORDER;
        this.setMember(member);
        for (OrderItem orderItem : orderItems) {
            this.addOrderItem(orderItem);
        }
        this.setTotalPrice();
    }

    //== 연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrderList().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    private void setTotalPrice() {
        this.totalPrice = this.orderItemList.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

    //== 비지니스 로직==//
    public void cancelOrder() {
        for (OrderItem orderItem : orderItemList) {
            orderItem.cancel();
        }
        status = OrderStatus.CANCEL;
    }



}
