package myproject.shoppingmall.repository;

import myproject.shoppingmall.domain.Address;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.item.Image;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.domain.order.Delivery;
import myproject.shoppingmall.domain.order.Order;
import myproject.shoppingmall.domain.order.OrderItem;
import myproject.shoppingmall.domain.order.OrderSearch;
import myproject.shoppingmall.dto.OrderDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

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

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

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
    
    @Test
    void 내주문조회하기(){

        //given
        Member member1 = Member.builder()
                .loginId("qwe")
                .password("123")
                .name("qwe")
                .email("wee@ew.com")
                .address(new Address("city", "street", "zipcode"))
                .build();

        Member member2 = Member.builder()
                .loginId("asd")
                .password("123")
                .name("asd")
                .email("asd@ew.com")
                .address(new Address("city", "street", "zipcode"))
                .build();

        em.persist(member1);
        em.persist(member2);


        Item item1 = new Item("운동화", 23000, 10, 22L);
        Item item2 = new Item("축구화", 43000, 30, 22L);
        Item item3 = new Item("농구화", 53000, 30, 22L);
        Item item4 = new Item("테니스화", 63000, 30, 22L);

        Image image1 = Image.builder().imagePath("231").build();
        Image image2 = Image.builder().imagePath("231").build();
        Image image3 = Image.builder().imagePath("231").build();
        Image image4 = Image.builder().imagePath("231").build();
        Image image5 = Image.builder().imagePath("231").build();
        Image image6 = Image.builder().imagePath("231").build();
        Image image7 = Image.builder().imagePath("231").build();
        Image image8 = Image.builder().imagePath("231").build();

        item1.addImage(image1);
        item2.addImage(image2);
        item3.addImage(image3);
        item4.addImage(image4);
        item1.addImage(image5);
        item2.addImage(image6);
        item3.addImage(image7);
        item4.addImage(image8);

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

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        em.persist(orderItem1);
        em.persist(orderItem2);

        Order order = Order.builder()
                .delivery(Delivery.builder().address(member1.getAddress()).build())
                .member(member1)
                .orderItems(orderItems)
                .build();

        em.persist(order);
        em.flush();
        em.clear();

        // when
        Page<Order> myOrderList = orderRepository.getMyOrderList(member1.getId(), new OrderSearch(), PageRequest.of(0, 10));

        List<Order> content = myOrderList.getContent();
        int totalPages = myOrderList.getTotalPages();

        System.out.println(content.size());

        for (Order Order : content) {
            System.out.println("orderDto.getOrderId() = " + Order.getId());
            System.out.println("orderDto.getMainOrderItemName() = " + Order.getOrderItemList().get(0).getItem().getImageList().get(0));
            System.out.println("orderDto.getTotalPrice() = " + Order.getTotalPrice());
        }

        System.out.println("totalPages = " + totalPages);
        // then
        
    }

}