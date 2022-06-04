package myproject.shoppingmall.domain.category.repository;


import myproject.shoppingmall.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
