package myproject.shoppingmall.domain.item;

import lombok.Getter;

@Getter
public enum Sorter {
    BYPRICE("가격순"), BYDATE("최신순");

    private String sorter;

    Sorter(String sorter) {
        this.sorter = sorter;
    }
}
