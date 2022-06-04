//package myproject.shoppingmall.repository;
//
//import myproject.shoppingmall.domain.member.entity.Member;
//import myproject.shoppingmall.domain.cart.entity.Cart;
//import myproject.shoppingmall.domain.cartItem.entity.CartItem;
//import myproject.shoppingmall.domain.item.entity.Item;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//@SpringBootTest
//@Transactional
//class CartRepositoryTest {
//
//    @Autowired
//    CartRepository cartRepository;
//
//    @Autowired
//    CartItemRepository cartItemRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    void 장바구니만들기(){
//        //given
//        Member member = Member.builder()
//                .loginId("thsckdgus0")
//                .name("thsckd")
//                .password("123123")
//                .email("123")
//                .build();
//
//        em.persist(member);
//
//        Item item1 = new Item("운동화", 23000, 10, 22L);
//        Item item2 = new Item("축구화", 43000, 30, 22L);
//
//        em.persist(item1);
//        em.persist(item2);
//
//        CartItem cartItem1 = new CartItem(item1.getId(), 5);
//        CartItem cartItem2 = new CartItem(item2.getId(), 10);
//
//        Cart cart = Cart.builder().member(member).build();
//
//        cart.addCartItem(cartItem1);
//        cart.addCartItem(cartItem2);
//
//        em.persist(cart);
//
//        em.flush();
//        em.clear();
//        // when
//
//        Cart findCart = cartRepository.findById(cart.getId()).get();
//        CartItem findCartItem1 = cartItemRepository.findById(cartItem1.getItemId()).get();
//        CartItem findCartItem2 = cartItemRepository.findById(cartItem2.getItemId()).get();
//
//        // then
//
//    }
//
//    @Test
//    void 멤버아이디로장바구니찾기(){
//        //given
//
//        Member member = Member.builder()
//                .loginId("thsckdgus0")
//                .name("thsckd")
//                .password("123123")
//                .email("123")
//                .build();
//
//        em.persist(member);
//
//        Item item1 = new Item("운동화", 23000, 10, 22L);
//        Item item2 = new Item("축구화", 43000, 30, 22L);
//
//        em.persist(item1);
//        em.persist(item2);
//
//        CartItem cartItem1 = new CartItem(item1.getId(), 5);
//        CartItem cartItem2 = new CartItem(item2.getId(), 10);
//
//        Cart cart = Cart.builder().member(member).build();
//
//        cart.addCartItem(cartItem1);
//        cart.addCartItem(cartItem2);
//
//        em.persist(cart);
//
//        em.flush();
//        em.clear();
//
//        // when
//
//        Cart findCart = cartRepository.findByMemberId(member.getId());
//
//        // then
//
//        Assertions.assertThat(findCart.getCartItemList().size()).isEqualTo(2);
//
//    }
//
//}