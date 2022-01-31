package myproject.shoppingmall.domain.item;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.aop.BaseEntity;
import myproject.shoppingmall.exception.NotEnoughStockException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int price;

    private int stockQuantity;

    private String color;

    //TODO 카테고리 수정해야함
    private Long categoryId;

    @Builder
    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    //==비즈니스 로직==//

    // 재고수량이 0보다 작으면
    public void removeStock(int orderQuantity) {
        int restStock = this.stockQuantity - orderQuantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 0보다 작습니다.");
        }
        this.stockQuantity = restStock;
    }

    public void addStock(int orderQuantity) {
        this.stockQuantity += orderQuantity;

    }
}
