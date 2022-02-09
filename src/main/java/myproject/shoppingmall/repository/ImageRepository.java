package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.item.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
