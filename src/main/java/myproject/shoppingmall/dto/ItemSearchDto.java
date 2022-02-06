package myproject.shoppingmall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import myproject.shoppingmall.domain.item.Item;

@Getter
public class ItemSearchDto {

    private Long id;
    private String name;
    private String imagePath;
    private int price;
    private int stockQuantity;
    private Long categoryId;

    // TODO 현재 DB에서 가져올 때, 구체 클래스(Clothes, Shoes)로 가져오지 못함 - 수정 필요
    @QueryProjection
    public ItemSearchDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.imagePath = item.getImagePath();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        this.categoryId = item.getCategoryId();
    }
}
