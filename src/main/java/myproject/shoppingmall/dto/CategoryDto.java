package myproject.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CategoryDto {
    private Long categoryId;
    private String categotyName;
    private Long parentId;
    private List<CategoryDto> subCategories;

    @Builder
    public CategoryDto(Long categoryId, String categotyName, Long parentId) {
        this.categoryId = categoryId;
        this.categotyName = categotyName;
        this.parentId = parentId;
    }
}
