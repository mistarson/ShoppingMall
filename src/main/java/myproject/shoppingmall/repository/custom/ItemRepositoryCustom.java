package myproject.shoppingmall.repository.custom;

import myproject.shoppingmall.domain.item.ItemSearch;
import myproject.shoppingmall.dto.ItemSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    Page<ItemSearchDto> findAll(ItemSearch itemSearch, Pageable pageable);

}
