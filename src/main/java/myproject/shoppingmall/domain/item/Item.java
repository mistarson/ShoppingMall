package myproject.shoppingmall.domain.item;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import myproject.shoppingmall.auditing.BaseEntity;
import myproject.shoppingmall.exception.NotEnoughStockException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ctype")
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

    private ClothesType clothesType;

    //TODO 카테고리 수정해야함
    private Long categoryId;


    public Item(String name, int price, int stockQuantity, String color, ClothesType clothesType,Long categoryId) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.color = color;
        this.clothesType = clothesType;
        this.categoryId = categoryId;
    }

    //==비즈니스 로직==//
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