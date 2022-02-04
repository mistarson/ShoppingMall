package myproject.shoppingmall.domain.item;

public class ItemSearch {
    private String name;
    private Sorter sorter;
    private Long categoryId;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
