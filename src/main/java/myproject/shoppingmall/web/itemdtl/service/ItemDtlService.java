package myproject.shoppingmall.web.itemdtl.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.item.service.ItemService;
import myproject.shoppingmall.domain.itemImage.service.ItemImageService;
import myproject.shoppingmall.web.dto.ItemDtlDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemDtlService {

    private final ItemService itemService;
    private final ItemImageService itemImageService;

    public ItemDtlDto getItemDetail(Long itemId) {
        Item item = itemService.findByIdFetchItemImages(itemId);
        return ItemDtlDto.from(item);
    }
}
