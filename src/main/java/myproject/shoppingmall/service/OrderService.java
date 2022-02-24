package myproject.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.Member;
import myproject.shoppingmall.domain.order.Delivery;
import myproject.shoppingmall.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;

//    @Transactional
//    public Long createOrder(Long memberId, List<Long> orderItemIdList) throws Exception {
//
//        Member member = memberService.findById(memberId);
//
//        Delivery delivery = Delivery.builder().address(member.getAddress()).build();
//
//
//
//    }


}


