package myproject.shoppingmall.web.shopMain.dto;

import lombok.Builder;
import lombok.Getter;
import myproject.shoppingmall.domain.item.entity.Item;

@Getter
@Builder
public class ItemSearchDto {

    private static final int REP_IMAGE = 0;

    private Long itemId;

    private String itemName;

    private String imageUrl;

    private int price;

    private Long categoryId;


    public static ItemSearchDto of(Item item) {
        return ItemSearchDto.builder()
                .itemId(item.getId())
                .itemName(item.getItemName())
                .imageUrl(item.getItemImageList().get(REP_IMAGE).getImageUrl())
                .price(item.getPrice())
                .build();
    }
}
