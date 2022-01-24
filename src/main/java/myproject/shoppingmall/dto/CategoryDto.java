package myproject.shoppingmall.dto;


import myproject.shoppingmall.domain.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    private String name;

    private String parentCategoryName;
    private List<CategoryDto> children;

    public CategoryDto(Category category) {
        this.name = category.getName();
        this.parentCategoryName = category.getParentCategory() == null ? "clothes" : category.getParentCategory().getName();
        this.children = category.getChildCategory() == null ?
                null : category.getChildCategory().stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .build();
    }
}
