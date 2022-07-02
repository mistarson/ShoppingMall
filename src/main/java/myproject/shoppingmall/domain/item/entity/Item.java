package myproject.shoppingmall.domain.item.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.audit.BaseEntity;
import myproject.shoppingmall.domain.itemImage.entity.ItemImage;
import myproject.shoppingmall.global.error.exception.ErrorCode;
import myproject.shoppingmall.global.error.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String itemName;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImage> itemImageList = new ArrayList<>();

    private int price;

    private int stockQuantity;

    private Long categoryId;

    @Builder
    public Item(String name, String itemDetail, int price, int stockQuantity) {
        this.itemName = name;
        this.itemDetail = itemDetail;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public static Item createItem(Item item, List<ItemImage> itemImageList) {
        Item saveItem = Item.builder()
                .name(item.getItemName())
                .itemDetail(item.getItemDetail())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .build();

        saveItem.addItemList(itemImageList);

        return saveItem;
    }

    //==연관관계 편의 메소드==//
    public void addItemList(List<ItemImage> itemImageList) {
        this.itemImageList = itemImageList;
        for (ItemImage itemImage : itemImageList) {
            itemImage.addItem(this);
        }
    }

    //==비즈니스 로직==//
    public void removeStock(int orderQuantity) {
        int restStock = this.stockQuantity - orderQuantity;
        if (restStock < 0) {
            throw new NotEnoughStockException(ErrorCode.REST_STOCK_NOT_EXISTS);
        }
        this.stockQuantity = restStock;
    }

    public void addStock(int orderQuantity) {
        this.stockQuantity += orderQuantity;

    }
}
