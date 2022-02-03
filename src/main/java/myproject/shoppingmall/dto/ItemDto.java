package myproject.shoppingmall.dto;

import lombok.Getter;
import lombok.ToString;
import myproject.shoppingmall.domain.item.Clothes;
import myproject.shoppingmall.domain.item.ClothesSize;
import myproject.shoppingmall.domain.item.ClothesType;
import myproject.shoppingmall.domain.item.Item;

@ToString
@Getter
public class ItemDto {

    private String name;
    private int price;
    private int stockQuantity;
    private String color;
    private Long categoryId;
    private ClothesType clothesType;
    private ClothesSize clothesSize;
    private int shoesSize;

    // TODO 현재 DB에서 가져올 때, 구체 클래스(Clothes, Shoes)로 가져오지 못함 - 수정 필요
    public ItemDto(Item item) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        this.color = item.getColor();
        this.categoryId = item.getCategoryId();
        this.clothesType = item.getClothesType();
    }

}
