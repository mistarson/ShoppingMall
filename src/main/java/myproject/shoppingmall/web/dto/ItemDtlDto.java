package myproject.shoppingmall.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.itemImage.entity.ItemImage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Builder
public class ItemDtlDto {

    private Long itemId;
    private String itemName;
    private List<String> imageUrlList = new ArrayList<>();
    private int price;
    private int stockQuantity;
    private Long categoryId;

    public static ItemDtlDto from(Item item) {

        List<ItemImage> itemImageList = item.getItemImageList();
        List<String> imageUrlList = itemImageList.stream()
                .map(ItemImage::getImageUrl).collect(Collectors.toList());

        return ItemDtlDto.builder()
                .itemId(item.getId())
                .itemName(item.getItemName())
                .imageUrlList(imageUrlList)
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .build();
    }

}
