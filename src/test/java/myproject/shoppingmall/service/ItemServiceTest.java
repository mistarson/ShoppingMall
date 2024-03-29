package myproject.shoppingmall.service;

import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.itemImage.entity.ItemImage;
import myproject.shoppingmall.web.dto.ItemSearchDto;
import myproject.shoppingmall.web.form.ItemForm;
import myproject.shoppingmall.web.dto.ItemDto;
import myproject.shoppingmall.domain.itemImage.repository.ItemImageRepository;
import myproject.shoppingmall.domain.item.repository.ItemRepository;
import myproject.shoppingmall.web.item.serivce.ItemService;
import myproject.shoppingmall.web.item.search.ItemSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemImageRepository imageRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void init() {
        ItemImage image1 = ItemImage.builder().imagePath("qwe").build();
        ItemImage image2 = ItemImage.builder().imagePath("qwe").build();
        ItemImage image3 = ItemImage.builder().imagePath("qwe").build();
        ItemImage image4 = ItemImage.builder().imagePath("qwe").build();
        ItemImage image5 = ItemImage.builder().imagePath("qwe").build();
        ItemImage image6 = ItemImage.builder().imagePath("qwe").build();

        Item item1 = new Item("운동화", 23000, 10, 22L, image1, image6);
        Item item2 = new Item("축구화", 50000, 10, 22L, image2);
        Item item3 = new Item("바지",30000, 10, 14L, image3);
        Item item4 = new Item("맨투맨", 40000, 10, 12L, image4);
        Item item5 = new Item("티셔츠", 10000, 10, 11L, image5);

        imageRepository.save(image1);
        imageRepository.save(image2);
        imageRepository.save(image3);
        imageRepository.save(image4);
        imageRepository.save(image5);
        imageRepository.save(image6);

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

        // when
        Long saveItemId = itemService.saveItem(itemForm);
        em.flush();
        em.clear();

        // then
        Item findItem = itemRepository.findById(saveItemId).get();


        assertThat(findItem.getName()).isEqualTo(itemForm.getName());


    }

    @Test
    void 아이템조회(){
        //given
        ItemImage image1 = ItemImage.builder().imagePath("qwe").build();
        ItemImage image2 = ItemImage.builder().imagePath("qwe").build();
        Item item = new Item("운동화", 23000, 10, 22L, image1, image2);

        imageRepository.save(image1);
        imageRepository.save(image2);

        Item savedItem = itemRepository.save(item);

        em.flush();
        em.clear();

        // when
        ItemDto findItem = itemService.findItem(savedItem.getId());

        // then
        assertThat(findItem.getName()).isEqualTo("운동화");
        assertThat(findItem.getImageList().get(0)).isEqualTo("qwe");
        assertThat(findItem.getImageList().get(1)).isEqualTo("qwe");
        assertThat(findItem.getImageList().size()).isEqualTo(2);

    }

    @Test
    void 검색아이템전체조회() {
        //given

        //when

        ItemSearch itemSearch = new ItemSearch();

        itemSearch.setName("운동");

        Page<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch, PageRequest.of(0, 5));

        //then
        for (ItemSearchDto dto : allForSearch.getContent()) {
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getPrice());
            System.out.println(dto.getCategoryId());
            System.out.println(dto.getStockQuantity());
            System.out.println(dto.getPrice());
            System.out.println(dto.getImageList().get(0));
        }

    }
//
//    @Test
//    void 검색이름에따른조회() {
//        //given
//
//        //when
//
//        ItemSearch itemSearch = new ItemSearch();
//        itemSearch.setName("운동화");
//
//        Page<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch,PageRequest.of(0, 5));
//
//        //then
//        assertThat(allForSearch.size()).isEqualTo(1);
//        assertThat(allForSearch.get(0).getName()).isEqualTo("운동화");
//        assertThat(allForSearch.get(0).getImageList().size()).isEqualTo(2);
//
//    }
//
//    @Test
//    void 검색카테고리에따른조회() {
//        //given
//
//        //when
//
//        ItemSearch itemSearch = new ItemSearch();
//        itemSearch.setCategoryId(22L);
//
//        List<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch);
//
//        //then
//        assertThat(allForSearch.size()).isEqualTo(2);
//        assertThat(allForSearch.get(0).getName()).isEqualTo("축구화");
//        assertThat(allForSearch.get(1).getName()).isEqualTo("운동화");
//
//    }
//
//    @Test
//    void 검색가격순에따른조회() {
//        //given
//
//        //when
//
//        ItemSearch itemSearch = new ItemSearch();
//        itemSearch.setSorter(ItemSorter.BYPRICE);
//
//        List<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch);
//
//        //then
//        assertThat(allForSearch.size()).isEqualTo(5);
//        assertThat(allForSearch.get(0).getName()).isEqualTo("축구화");
//
//    }
//
//    @Test
//    void 검색가격과카테고리에따른조회() {
//        //given
//
//        //when
//
//        ItemSearch itemSearch = new ItemSearch();
//        itemSearch.setCategoryId(22L);
//        itemSearch.setSorter(ItemSorter.BYPRICE);
//
//        List<ItemSearchDto> allForSearch = itemService.findAllForSearch(itemSearch);
//
//        //then
//        assertThat(allForSearch.size()).isEqualTo(2);
//        assertThat(allForSearch.get(0).getName()).isEqualTo("축구화");
//
//    }

}