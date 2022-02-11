package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.Address;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.domain.order.Delivery;
import myproject.shoppingmall.domain.order.Order;
import myproject.shoppingmall.domain.order.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EntityManager em;

    @Test
    void 주문생성하기(){
        //given
        Member member = Member.builder()
                .loginId("qwe")
                .password("123")
                .name("qwe")
                .email("wee@ew.com")
                .address(new Address("city", "street", "zipcode"))
                .build();

        em.persist(member);


        Item item1 = new Item("운동화", 23000, 10, 22L);
        Item item2 = new Item("축구화", 43000, 30, 22L);

        em.persist(item1);
        em.persist(item2);

        OrderItem orderItem1 = OrderItem.builder()
                .item(item1)
                .orderQuantity(10)
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .item(item2)
                .orderQuantity(10)
                .build();

        OrderItem[] orderItems = new OrderItem[2];
        orderItems[0] = orderItem1;
        orderItems[1] = orderItem2;

        em.persist(orderItem1);
        em.persist(orderItem2);

        Order order = Order.builder()
                .delivery(Delivery.builder().address(member.getAddress()).build())
                .member(member)
                .orderItems(orderItems)
                .build();

        em.persist(order);
        em.flush();
        em.clear();

        // when
        Order findOrder = orderRepository.findById(order.getId()).get();

        // then
        assertThat(findOrder.getOrderItemList().size()).isEqualTo(2);
        assertThat(findOrder.getMember().getName()).isEqualTo(member.getName());

    }

}