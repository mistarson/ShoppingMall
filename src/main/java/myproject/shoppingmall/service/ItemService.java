package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.form.ItemForm;
import myproject.shoppingmall.domain.item.Clothes;
import myproject.shoppingmall.domain.item.ClothesType;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.domain.item.Shoes;
import myproject.shoppingmall.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(ItemForm itemForm) {

        if (itemForm.getClothesType() == ClothesType.TOP || itemForm.getClothesType() == ClothesType.OUTER || itemForm.getClothesType() == ClothesType.PANTS) {
            Clothes newClothes = itemForm.itemFormToClothesEntity();
            Clothes savedClothes = itemRepository.save(newClothes);

            return savedClothes.getId();

        } else{
            Shoes newShoes = itemForm.itemFormToShoesEntity();
            Shoes savedShoes = itemRepository.save(newShoes);

            return savedShoes.getId();

        }


    }

}
