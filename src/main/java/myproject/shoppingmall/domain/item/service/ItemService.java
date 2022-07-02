package myproject.shoppingmall.domain.item.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.global.error.exception.EntityNotFoundException;
import myproject.shoppingmall.global.error.exception.ErrorCode;
import myproject.shoppingmall.web.shopMain.search.ItemSearch;
import myproject.shoppingmall.domain.item.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long registerItem(Item item) {
        return itemRepository.save(item).getId();
    }


    public Item findByItemId(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NON_EXISTENT_ITEM));
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

    }

    public Optional<Item> findByItemName(String itemName) {
        return itemRepository.findByItemName(itemName);
    }

    public Page<Item> findAllForSearch(ItemSearch itemSearch, Pageable pageable) {
        return itemRepository.findAllWithSearch(itemSearch, pageable);
    }

}
