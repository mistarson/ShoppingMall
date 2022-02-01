package myproject.shoppingmall.domain.form;

import lombok.Builder;
import lombok.Getter;
import myproject.shoppingmall.domain.item.*;

@Getter
public class ItemForm {

    private String name;
    private int price;
    private int stockQuantity;
    private String color;
    private Long categoryId;
    private ClothesType clothesType;
    private ClothesSize clothesSize;
    private int shoesSize;

    public ItemForm(String name, int price, int stockQuantity, String color, Long categoryId, ClothesType clothesType) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.color = color;
        this.categoryId = categoryId;
        this.clothesType = clothesType;
    }

    public Clothes itemFormToClothesEntity() {

        return Clothes.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .color(color)
                .categoryId(categoryId)
                .clothesType(clothesType)
                .clothesSize(clothesSize)
                .build();
    }

    public Shoes itemFormToShoesEntity() {

        return Shoes.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .color(color)
                .categoryId(categoryId)
                .clothesType(clothesType)
                .shoesSize(shoesSize)
                .build();
    }

}
