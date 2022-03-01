package myproject.shoppingmall.domain.item;

import lombok.Getter;

@Getter
public enum ItemSorter {
    BYPRICE("가격순"), BYDATE("최신순");

    private String sorter;

    ItemSorter(String sorter) {
        this.sorter = sorter;
    }
}
