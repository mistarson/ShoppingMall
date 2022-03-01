package myproject.shoppingmall.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearch {
    private String name;
    private ItemSorter sorter;
    private Long categoryId;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
