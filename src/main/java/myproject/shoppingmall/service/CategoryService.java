//package myproject.shoppingmall.service;
//
//import lombok.RequiredArgsConstructor;
//import myproject.shoppingmall.domain.Category;
//import myproject.shoppingmall.dto.CategoryDto;
//import myproject.shoppingmall.repository.CategotyRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//@Service
//@RequiredArgsConstructor
//public class CategoryService {
//
//    private final CategotyRepository categotyRepository;
//
//    public Long saveCategory(CategoryDto categoryDto) {
//        Category category = categoryDto.toEntity();
//
//        if (!StringUtils.hasText(categoryDto.getParentCategoryName())) {
//            if (categotyRepository.existsByName(categoryDto.getParentCategoryName())) {
//                throw new RuntimeException("이름이 같을 수 없습니다.");
//            }
//
//            categotyRepository.findByName(categoryDto.getParentCategoryName())
//        }
//    }
//}
