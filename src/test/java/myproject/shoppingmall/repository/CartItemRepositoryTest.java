//package myproject.shoppingmall.repository;
//
//import myproject.shoppingmall.domain.member.entity.Member;
//import myproject.shoppingmall.domain.cart.entity.Cart;
//import myproject.shoppingmall.domain.cartItem.entity.CartItem;
//import myproject.shoppingmall.domain.item.Image;
//import myproject.shoppingmall.domain.item.entity.Item;
//import myproject.shoppingmall.web.dto.CartItemDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class CartItemRepositoryTest {
//
//    @Autowired
//    CartItemRepository cartItemRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    void 장바구니불러오기(){
//        //given
//        Member member = Member.builder()
//                .loginId("thsckdgus0")
//                .name("thsckd")
//                .password("123123")
//                .email("123")
//                .build();
//        em.persist(member);
//
//        Image image1 = Image.builder().imagePath("clothes/1/main.jpg").build();
//        Image image2 = Image.builder().imagePath("clothes/1/1.jpg").build();
//        Image image3 = Image.builder().imagePath("clothes/2/main.jpg").build();
//        Image image4 = Image.builder().imagePath("clothes/2/1.jpg").build();
//        Item item1 = new Item("운동화", 23000, 10, 22L);
//        Item item2 = new Item("축구화", 43000, 30, 22L);
//        item1.addImage(image1);
//        item1.addImage(image2);
//        item2.addImage(image3);
//        item2.addImage(image4);
//        em.persist(item1);
//        em.persist(item2);
//
//        CartItem cartItem1 = new CartItem(item1.getId(), 10);
//        CartItem cartItem2 = new CartItem(item2.getId(), 10);
//        Cart cart = Cart.builder().member(member).build();
//        cart.addCartItem(cartItem1);
//        cart.addCartItem(cartItem2);
//        em.persist(cart);
//
//        em.flush();
//        em.clear();
//        // when
//
////        List<CartItemDto> allCartItem = cartItemRepository.findAllCartItem(member.getId());
////
////        // then
////        for (CartItemDto cartItemDto : allCartItem) {
////            System.out.println(cartItemDto);
////        }
//
//    }
//
//}