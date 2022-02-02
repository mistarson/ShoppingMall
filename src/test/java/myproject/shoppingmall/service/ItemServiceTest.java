package myproject.shoppingmall.service;

import myproject.shoppingmall.domain.form.ItemForm;
import myproject.shoppingmall.domain.item.Clothes;
import myproject.shoppingmall.domain.item.ClothesSize;
import myproject.shoppingmall.domain.item.ClothesType;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em;
    
    @Test
    void 아이템저장하기(){
        //given
        ItemForm itemForm = new ItemForm("바지", 3000, 10, "white", 1L, ClothesType.PANTS);
//        itemForm.setClothesSize(ClothesSize.LARGE);

        // when
        Long saveItemId = itemService.saveItem(itemForm);
        em.flush();
        em.clear();

        // then
        Item findItem = itemRepository.findById(saveItemId).get();


        Assertions.assertThat(findItem.getName()).isEqualTo(itemForm.getName());


    }

}