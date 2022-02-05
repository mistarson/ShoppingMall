package myproject.shoppingmall.service;

import myproject.shoppingmall.domain.item.ItemSearch;
import myproject.shoppingmall.domain.item.Sorter;
import myproject.shoppingmall.dto.ItemSearchDto;
import myproject.shoppingmall.form.ItemForm;
import myproject.shoppingmall.domain.item.ClothesType;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.dto.ItemDto;
import myproject.shoppingmall.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void init() {
        Item item1 = new Item("운동화", "qwe", 23000, 10,22L );
        Item item2 = new Item("축구화", "qwe", 50000, 10,22L);
        Item item3 = new Item("바지", "qwe", 30000, 10,14L);
        Item item4 = new Item("맨투맨", "qwe", 40000, 10,12L);
        Item item5 = new Item("티셔츠", "qwe", 10000, 10,11L);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);

        em.flush();
        em.clear();
    }

    @Test
    void 아이템저장하기() {

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
    void 검색아이템전체조회() {
        //given

        //when

        ItemSearch itemSearch = new ItemSearch();

        List<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch);

        //then
        assertThat(allForSearch.size()).isEqualTo(5);

    }

    @Test
    void 검색이름에따른조회() {
        //given

        //when

        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setName("운동화");

        List<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch);

        //then
        assertThat(allForSearch.size()).isEqualTo(1);
        assertThat(allForSearch.get(0).getName()).isEqualTo("운동화");

    }

    @Test
    void 검색카테고리에따른조회() {
        //given

        //when

        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setCategoryId(22L);

        List<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch);

        //then
        assertThat(allForSearch.size()).isEqualTo(2);
        assertThat(allForSearch.get(0).getName()).isEqualTo("축구화");
        assertThat(allForSearch.get(1).getName()).isEqualTo("운동화");

    }

    @Test
    void 검색가격순에따른조회() {
        //given

        //when

        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setSorter(Sorter.BYPRICE);

        List<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch);

        //then
        assertThat(allForSearch.size()).isEqualTo(5);
        assertThat(allForSearch.get(0).getName()).isEqualTo("축구화");

    }

    @Test
    void 검색가격과카테고리에따른조회() {
        //given

        //when

        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setCategoryId(22L);
        itemSearch.setSorter(Sorter.BYPRICE);

        List<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch);

        //then
        assertThat(allForSearch.size()).isEqualTo(2);
        assertThat(allForSearch.get(0).getName()).isEqualTo("축구화");

    }

}