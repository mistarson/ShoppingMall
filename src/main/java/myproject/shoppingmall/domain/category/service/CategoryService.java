package myproject.shoppingmall.domain.category.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.web.dto.CategoryDto;
import myproject.shoppingmall.domain.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto createCategoryRoot() {
        // Long -> parentId로 groupingBy
        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll().stream().map(c1 ->
                        CategoryDto.builder()
                                .categoryId(c1.getId())
                                .categoryName(c1.getCategoryName())
                                .parentId(c1.getParentId())
                                .build())
                .collect(Collectors.groupingBy(CategoryDto::getParentId));

        CategoryDto rootCategoryDto = CategoryDto.builder()
                .categoryId(0L)
                .categoryName("ROOT")
                .parentId(null)
                .build();

        addSubCategories(rootCategoryDto, groupingByParent);

        return rootCategoryDto;
    }

    private void addSubCategories(CategoryDto parent, Map<Long, List<CategoryDto>> groupingByParent) {

        List<CategoryDto> subCategories = groupingByParent.get(parent.getCategoryId());

        if (subCategories == null) {
            return;
        }

        parent.setSubCategories(subCategories);

        subCategories.stream().forEach(s -> {
            addSubCategories(s, groupingByParent);
        });
    }
}
