package myproject.shoppingmall.domain.item.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.audit.BaseEntity;
import myproject.shoppingmall.domain.itemImage.entity.ItemImage;
import myproject.shoppingmall.global.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "ctype")
public class Item extends BaseEntity {
// TODO 추후에 사이즈, 컬러 추가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemImage> imageList = new ArrayList<>();

    private int price;

    private int stockQuantity;

    private Long categoryId;


    @Builder
    public Item(String name, int price, int stockQuantity, Long categoryId, ItemImage... imageList) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        for (ItemImage image : imageList) {
            addImage(image);
        }
    }

    //==연관관계 편의 메소드==//
    public void addImage(ItemImage image) {
        imageList.add(image);
        image.setImage(this);
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
