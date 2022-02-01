package myproject.shoppingmall.domain.order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.auditing.BaseEntity;
import myproject.shoppingmall.domain.Address;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id", unique = true, nullable = false)
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    public Delivery(Address address) {
        this.address = address;
        this.status = DeliveryStatus.READY;
    }
}
