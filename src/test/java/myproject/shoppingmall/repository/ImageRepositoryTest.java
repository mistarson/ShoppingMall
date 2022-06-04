//package myproject.shoppingmall.repository;
//
//import myproject.shoppingmall.domain.item.Image;
//import myproject.shoppingmall.domain.item.entity.Item;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//public class ImageRepositoryTest {
//
//    @Autowired
//    ImageRepository imageRepository;
//
//    @Autowired
//    ItemRepository itemRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    void 아이템이미지경로저장() {
//        //given
//        Image image1 = Image.builder().imagePath("clothes/1.jpg").build();
//        Image image2 = Image.builder().imagePath("shoes/2.jpg").build();
//
//        Item item = new Item("운동화", 23000, 10, 22L, image1, image2);
//        imageRepository.save(image1);
//        imageRepository.save(image2);
//        Item saveItem = itemRepository.save(item);
//        em.flush();
//        em.clear();
//
//        // when
//        List<Image> findImageList = imageRepository.findAll();
//        Item findItem = itemRepository.findById(saveItem.getId()).get();
//
//
//        // then
//        assertThat(findImageList.size()).isEqualTo(2);
//        assertThat(findItem.getImageList().size()).isEqualTo(2);
//
//    }
//}