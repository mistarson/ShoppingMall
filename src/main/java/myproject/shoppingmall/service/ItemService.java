package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.domain.item.ItemSearch;
import myproject.shoppingmall.dto.ItemDto;
import myproject.shoppingmall.dto.ItemSearchDto;
import myproject.shoppingmall.form.ItemForm;
import myproject.shoppingmall.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ItemSearchDto> findAll(ItemSearch itemSearch) {
        return itemRepository.findAll(itemSearch);
    }

}
