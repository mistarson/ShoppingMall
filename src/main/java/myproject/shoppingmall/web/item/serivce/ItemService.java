package myproject.shoppingmall.web.item.serivce;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.web.item.search.ItemSearch;
import myproject.shoppingmall.web.dto.ItemDto;
import myproject.shoppingmall.web.dto.ItemSearchDto;
import myproject.shoppingmall.web.form.ItemForm;
import myproject.shoppingmall.domain.item.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(ItemForm itemForm) {

        Item item = itemForm.itemFormToEntity();
        Item savedItem = itemRepository.save(item);

        return savedItem.getId();
    }


    public ItemDto findItem(Long itemId) {
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return new ItemDto(findItem);
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

    }

    public Page<ItemSearchDto> findAllForSearch(ItemSearch itemSearch, Pageable pageable) {
        return itemRepository.findAll(itemSearch, pageable);
    }

}
