package myproject.shoppingmall.service;

import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.cart.Cart;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.dto.CartItemDto;
import myproject.shoppingmall.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    EntityManager em;
    
    @Test
    void 장바구니에아이템추가하기(){
        //given
        Member member = Member.builder()
                .loginId("thsckdgus0")
                .name("thsckd")
                .password("123123")
                .email("123")
                .build();

        em.persist(member);

        Item item1 = new Item("운동화", 23000, 10, 22L);
        Item item2 = new Item("축구화", 43000, 30, 22L);

        em.persist(item1);
        em.persist(item2);

        Cart cart = Cart.builder().member(member).build();

        CartItemDto cartItemDto1 = new CartItemDto(item1.getId(), 3);
        CartItemDto cartItemDto2 = new CartItemDto(item2.getId(), 5);

        em.persist(cart);

        em.flush();
        em.clear();
        
        // when

        cartService.addCartItem(member.getId(), cartItemDto1);
        cartService.addCartItem(member.getId(), cartItemDto2);

        Cart findCart = cartRepository.findByMemberId(member.getId());

        // then
        assertThat(findCart.getItemIdList().size()).isEqualTo(2);
        assertThat(findCart.getItemIdList().get(0).getItemId()).isEqualTo(1);
        
    }

    @Test
    void 장바구니에똑같은아이템추가(){
        //given
        Member member = Member.builder()
                .loginId("thsckdgus0")
                .name("thsckd")
                .password("123123")
                .email("123")
                .build();

        em.persist(member);

        Item item1 = new Item("운동화", 23000, 10, 22L);

        em.persist(item1);

        Cart cart = Cart.builder().member(member).build();

        CartItemDto cartItemDto1 = new CartItemDto(item1.getId(), 3);

        em.persist(cart);

        em.flush();
        em.clear();

        // when

        cartService.addCartItem(member.getId(), cartItemDto1);
        cartService.addCartItem(member.getId(), cartItemDto1);

        Cart findCart = cartRepository.findByMemberId(member.getId());


        // then
        assertThat(findCart.getItemIdList().size()).isEqualTo(1);
        assertThat(findCart.getItemIdList().get(0).getOrderQuantity()).isEqualTo(6);

    }

}