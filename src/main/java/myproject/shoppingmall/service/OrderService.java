package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.domain.order.Delivery;
import myproject.shoppingmall.domain.order.Order;
import myproject.shoppingmall.domain.order.OrderItem;
import myproject.shoppingmall.domain.order.OrderSearch;
import myproject.shoppingmall.form.DirectOrderItem;
import myproject.shoppingmall.form.RequestOrderItem;
import myproject.shoppingmall.form.RequestOrderItems;
import myproject.shoppingmall.repository.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ItemService itemService;
    private final CartService cartService;

    @Transactional
    public Long createOrder(Long memberId, RequestOrderItems requestOrderItems) throws Exception {

        Member member = memberService.findById(memberId);

        Delivery delivery = Delivery.builder().address(member.getAddress()).build();

        List<RequestOrderItem> requestOrderItemList = requestOrderItems.getOrderItemList();

        List<OrderItem> orderItemList = requestOrderItemList.stream().map(ro -> {
                    Item findItem = itemService.findById(ro.getItemId());
                    return OrderItem.builder().item(findItem).orderQuantity(ro.getOrderQuantity()).build();
                })
                .collect(Collectors.toList());

        Order order = Order.builder()
                .delivery(delivery)
                .member(member)
                .orderItems(orderItemList)
                .build();

        for (int i = 0; i < requestOrderItemList.size(); i++) {
            cartService.removeCartItem(memberId,requestOrderItemList.get(i).getItemId());
        }

        return orderRepository.save(order).getId();

    }


    @Transactional
    public Long createDirectOrder(Long memberId, DirectOrderItem directOrderItem) throws Exception {

        Member member = memberService.findById(memberId);

        Delivery delivery = Delivery.builder().address(member.getAddress()).build();

        OrderItem orderItem = OrderItem.builder()
                .item(itemService.findById(directOrderItem.getItemId()))
                .orderQuantity(directOrderItem.getOrderQuantity())
                .build();

        List<OrderItem> orderItems = new ArrayList<>();

        orderItems.add(orderItem);

        Order order = Order.builder()
                .delivery(delivery)
                .member(member)
                .orderItems(orderItems)
                .build();

        return orderRepository.save(order).getId();

    }

    public void getMyOrderList(Long memberId, OrderSearch orderSearch, Pageable pageable) {

        orderRepository.getMyOrderList(memberId, orderSearch, pageable);
    }
}


