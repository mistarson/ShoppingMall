package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.repository.custom.ItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
}
