package myproject.shoppingmall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import myproject.shoppingmall.domain.item.Image;
import myproject.shoppingmall.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemSearchDto {

    private Long id;
    private String name;
    private List<String> imageList = new ArrayList<>();
    private int price;
    private int stockQuantity;
    private Long categoryId;

    @QueryProjection
    public ItemSearchDto(Item item) {
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
