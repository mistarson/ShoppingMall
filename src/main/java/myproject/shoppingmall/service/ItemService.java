package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.dto.ItemDto;
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

//    @Transactional
//    public Long saveItem(ItemForm itemForm) {
//
//        if (itemForm.getClothesType() == ClothesType.TOP || itemForm.getClothesType() == ClothesType.OUTER || itemForm.getClothesType() == ClothesType.PANTS) {
//            Clothes newClothes = itemForm.itemFormToClothesEntity();
//            Clothes savedClothes = itemRepository.save(newClothes);
//
//            return savedClothes.getId();
//        } else{
//            Shoes newShoes = itemForm.itemFormToShoesEntity();
//            Shoes savedShoes = itemRepository.save(newShoes);
//
//            return savedShoes.getId();
//        }
//    }



    public ItemDto findItem(Long itemId) {
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return new ItemDto(findItem);
    }

    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream().map(ItemDto::new).collect(Collectors.toList());
    }

}
