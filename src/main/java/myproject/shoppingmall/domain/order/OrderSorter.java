package myproject.shoppingmall.domain.order;

import lombok.Getter;

@Getter
public enum OrderSorter {
    BYRECENTDATE("최신순"), BYOLDDATE("오래된순");

    private String sorter;

    OrderSorter(String sorter) {
        this.sorter = sorter;
    }
}
