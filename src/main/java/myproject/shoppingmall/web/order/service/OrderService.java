package myproject.shoppingmall.web.order.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.member.entity.Member;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.delivery.entity.Delivery;
import myproject.shoppingmall.domain.order.entity.Order;
import myproject.shoppingmall.domain.orderItem.entity.OrderItem;
import myproject.shoppingmall.web.login.service.MemberService;
import myproject.shoppingmall.web.cart.service.CartService;
import myproject.shoppingmall.web.item.serivce.ItemService;
import myproject.shoppingmall.web.order.search.OrderSearch;
import myproject.shoppingmall.web.dto.MemberDto;
import myproject.shoppingmall.web.dto.OrderDetailDto;
import myproject.shoppingmall.web.dto.OrderDto;
import myproject.shoppingmall.web.form.DirectOrderItem;
import myproject.shoppingmall.web.form.RequestOrderItem;
import myproject.shoppingmall.web.form.RequestOrderItems;
import myproject.shoppingmall.domain.order.repository.OrderRepository;
import org.springframework.data.domain.Page;
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
    public Long createOrder(RequestOrderItems requestOrderItems) throws Exception {

        MemberDto memberDto = memberService.getLoginMember();
        Member member = memberService.findById(memberDto.getId());

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
            cartService.removeCartItem(requestOrderItemList.get(i).getItemId());
        }

        return orderRepository.save(order).getId();

    }


    @Transactional
    public Long createDirectOrder(DirectOrderItem directOrderItem) throws Exception {

        MemberDto memberDto = memberService.getLoginMember();
        Member member = memberService.findById(memberDto.getId());

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

    public Page<OrderDto> getMyOrderList(OrderSearch orderSearch, Pageable pageable) throws Exception {

        MemberDto memberDto = memberService.getLoginMember();

        Page<Order> myOrderList = orderRepository.getMyOrderList(memberDto.getId(), orderSearch, pageable);


        return myOrderList.map(OrderDto::new);

    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

        order.cancelOrder();
    }

    public OrderDetailDto getOrderDetail(Long orderId) throws Exception{
        MemberDto loginMember = memberService.getLoginMember();

        Order order = orderRepository.getOrderDetail(loginMember.getId(), orderId);

        return new OrderDetailDto(order);
    }

}


