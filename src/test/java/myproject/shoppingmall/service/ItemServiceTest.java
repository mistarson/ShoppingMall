package myproject.shoppingmall.service;

import myproject.shoppingmall.form.ItemForm;
import myproject.shoppingmall.domain.item.ClothesType;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.dto.ItemDto;
import myproject.shoppingmall.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        ItemForm itemForm = new ItemForm("바지", "sd", 1000, 10, 1L);
//        itemForm.setClothesSize(ClothesSize.LARGE);

        // when
        Long saveItemId = itemService.saveItem(itemForm);
        em.flush();
        em.clear();

        // then
        Item findItem = itemRepository.findById(saveItemId).get();


        assertThat(findItem.getName()).isEqualTo(itemForm.getName());


    }

    @Test
    void 검색아이템조회() {
        //given
        Item item1 = new Item("운동화", "qwe", 23000, 10,22L );
        Item item2 = new Item("축구화", "qwe", 23000, 10,22L);
        Item item3 = new Item("바지", "qwe", 23000, 10,22L);
        Item item4 = new Item("맨투맨", "qwe", 23000, 10,22L);
        Item item5 = new Item("공항자켓", "qwe", 23000, 10,22L);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);

        em.flush();
        em.clear();
        //when


        //then

    }

}