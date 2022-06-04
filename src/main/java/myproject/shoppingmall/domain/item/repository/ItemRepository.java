package myproject.shoppingmall.domain.item.repository;

import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.item.repository.custom.ItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
}
