package myproject.shoppingmall.web.adminItem.form;

import lombok.Getter;
import lombok.Setter;
import myproject.shoppingmall.domain.item.entity.Item;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class RegisterItemForm {

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockQuantity;

    private List<MultipartFile> itemImageFiles;

    public Item toEntity() {
        return Item.builder()
                .name(itemName)
                .itemDetail(itemDetail)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }
}
