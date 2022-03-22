//package myproject.shoppingmall.service;
//
//import myproject.shoppingmall.dto.CategoryDto;
//import myproject.shoppingmall.repository.CategoryRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//@ExtendWith(MockitoExtension.class)
//class CategoryServiceTest {
//
//    @Autowired
//    CategoryService categoryService;
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Test
//    void 카테고리생성() {
//
//        CategoryDto categoryRoot = categoryService.createCategoryRoot();
//
//        assertThat(categoryRoot.getSubCategories().size()).isEqualTo(1);
//        assertThat(categoryRoot.getSubCategories().get(0).getSubCategories().size()).isEqualTo(4);
//
//
//    }
//
//
//}