package myproject.shoppingmall.web.shopMain.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.item.service.ItemService;
import myproject.shoppingmall.web.shopMain.dto.ItemSearchDto;
import myproject.shoppingmall.web.shopMain.search.ItemSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopMainService {

    private final ItemService itemService;

    public Page<ItemSearchDto> getItemsForSearch(ItemSearch itemSearch, Pageable pageable) {
        Page<Item> itemsWithSearch = itemService.findAllForSearch(itemSearch, pageable);

        return itemsWithSearch.map(ItemSearchDto::of);
    }
}
