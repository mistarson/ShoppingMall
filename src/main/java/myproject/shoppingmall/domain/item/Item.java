package myproject.shoppingmall.domain.item;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.auditing.BaseEntity;
import myproject.shoppingmall.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "ctype")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "item")
    private List<ItemImagePath> itemImagePathList;

    private int price;

    private int stockQuantity;

    private Long categoryId;


    @Builder
    public Item(String name, List<ItemImagePath> itemImagePathList, int price, int stockQuantity,Long categoryId) {
        this.name = name;
        this.itemImagePathList = itemImagePathList;
        this.price = price;
        this.stockQuantity = stockQuantity;
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
