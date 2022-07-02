package myproject.shoppingmall.domain.item.repository.custom;

import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.web.shopMain.search.ItemSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> findAllWithSearch(ItemSearch itemSearch, Pageable pageable);

}
