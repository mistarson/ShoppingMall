package myproject.shoppingmall.dto;

import lombok.Getter;
import lombok.ToString;
import myproject.shoppingmall.domain.item.Image;
import myproject.shoppingmall.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class ItemDto {

    private Long id;
    private String name;
    private List<String> imageList = new ArrayList<>();
    private int price;
    private int stockQuantity;
    private Long categoryId;

    // TODO 현재 DB에서 가져올 때, 구체 클래스(Clothes, Shoes)로 가져오지 못함 - 수정 필요
    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        this.categoryId = item.getCategoryId();
        for (Image image : item.getImageList()) {
            imageList.add(image.getImagePath());
        }
    }

}
