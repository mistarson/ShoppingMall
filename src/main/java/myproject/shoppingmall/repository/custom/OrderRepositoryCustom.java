package myproject.shoppingmall.repository.custom;

import myproject.shoppingmall.domain.order.OrderSearch;
import myproject.shoppingmall.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustom {
    Page<OrderDto> getMyOrderList(Long memberId, OrderSearch orderSearch, Pageable pageable);
}
