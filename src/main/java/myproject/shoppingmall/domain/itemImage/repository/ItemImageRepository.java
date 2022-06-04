package myproject.shoppingmall.domain.itemImage.repository;

import myproject.shoppingmall.domain.itemImage.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {

}
