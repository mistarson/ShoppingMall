package myproject.shoppingmall.repository.custom;

import myproject.shoppingmall.domain.item.ItemSearch;
import myproject.shoppingmall.dto.ItemSearchDto;

import java.util.List;

public interface ItemRepositoryCustom {

    List<ItemSearchDto> findAll(ItemSearch itemSearch);

}
