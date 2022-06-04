package myproject.shoppingmall.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class CartItemDto {
    private Long itemId;
    private String itemMainImagePath;
    private String itemName;
    private int itemPrice;
    private int orderQuantity;
    private int quantity;

    @QueryProjection
    public CartItemDto(Long itemId, String itemMainImagePath, String itemName, int itemPrice, int orderQuantity, int quantity) {
        this.itemId = itemId;
        this.itemMainImagePath = itemMainImagePath;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.orderQuantity = orderQuantity;
        this.quantity = quantity;
    }
}
