package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.dto.CategoryDto;
import myproject.shoppingmall.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto createCategoryRoot() {
        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll().stream().map(c1 ->
                        CategoryDto.builder()
                                .categoryId(c1.getId())
                                .categotyName(c1.getCategoryName())
                                .parentId(c1.getParentId()).build())
                .collect(Collectors.groupingBy(CategoryDto::getParentId));

        CategoryDto rootCategoryDto = CategoryDto.builder()
                .categoryId(0L)
                .categotyName("ROOT")
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
