package myproject.shoppingmall.domain.item.repository.custom;

import myproject.shoppingmall.web.item.search.ItemSearch;
import myproject.shoppingmall.web.dto.ItemSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<ItemSearchDto> findAll(ItemSearch itemSearch, Pageable pageable);

}
