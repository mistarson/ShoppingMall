package myproject.shoppingmall.web.form;

import lombok.Getter;
import myproject.shoppingmall.domain.item.entity.Item;

@Getter
public class ItemForm {

    private String name;
    private String imagePath;
    private int price;
    private int stockQuantity;
    private Long categoryId;

    public ItemForm(String name, String imagePath,int price, int stockQuantity, Long categoryId) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }

    public Item itemFormToEntity() {
        return Item.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .categoryId(categoryId)
                .build();
    }

//    public Clothes itemFormToClothesEntity() {
//
//        return Clothes.builder()
//                .name(name)
//                .price(price)
//                .stockQuantity(stockQuantity)
//                .color(color)
//                .categoryId(categoryId)
//                .clothesType(clothesType)
//                .clothesSize(clothesSize)
//                .build();
//    }
//
//    public Shoes itemFormToShoesEntity() {
//
//        return Shoes.builder()
//                .name(name)
//                .price(price)
//                .stockQuantity(stockQuantity)
//                .color(color)
//                .categoryId(categoryId)
//                .clothesType(clothesType)
//                .shoesSize(shoesSize)
//                .build();
//    }

}
