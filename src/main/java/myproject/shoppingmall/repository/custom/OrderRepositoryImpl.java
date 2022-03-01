package myproject.shoppingmall.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import myproject.shoppingmall.domain.item.ItemSorter;
import myproject.shoppingmall.domain.order.*;
import myproject.shoppingmall.dto.OrderDto;
import myproject.shoppingmall.dto.QOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

import static myproject.shoppingmall.domain.QMember.member;
import static myproject.shoppingmall.domain.item.QItem.item;
import static myproject.shoppingmall.domain.order.QDelivery.*;
import static myproject.shoppingmall.domain.order.QOrder.*;
import static myproject.shoppingmall.domain.order.QOrderItem.*;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<OrderDto> getMyOrderList(Long memberId, OrderSearch orderSearch, Pageable pageable) {
        List<OrderDto> results = queryFactory
                .select(new QOrderDto(order))
                .join(order.member, member).fetchJoin()
                .join(order.orderItemList, orderItem).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .from(order)
                .where(memberIdEq(memberId),
                        orderStatusEq(orderSearch.getOrderStatus()))
                .orderBy(sorter(orderSearch.getOrderSorter()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(order)
                .where(memberIdEq(memberId))
                .fetchCount();

        return new PageImpl<>(results, pageable, total);

    }
    private OrderSpecifier sorter(OrderSorter sorter) {
        if (sorter == OrderSorter.BYRECENTDATE) {
            return item.price.desc();
        } else if (sorter == OrderSorter.BYOLDDATE) {
            return item.createDate.desc();
        }
        return item.createDate.desc();
    }

    private BooleanBuilder memberIdEq(Long memberId) {
        return nullSafeBuilder(() -> member.id.eq(memberId));
    }

    private BooleanBuilder orderStatusEq(OrderStatus orderStatus) {
        return nullSafeBuilder(() -> order.status.eq(orderStatus));
    }

    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }
}
