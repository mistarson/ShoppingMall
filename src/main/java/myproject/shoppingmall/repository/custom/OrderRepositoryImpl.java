package myproject.shoppingmall.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import myproject.shoppingmall.domain.item.QImage;
import myproject.shoppingmall.domain.item.QItem;
import myproject.shoppingmall.domain.order.*;
import myproject.shoppingmall.dto.OrderDto;
import myproject.shoppingmall.dto.QOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

import static com.querydsl.jpa.JPAExpressions.*;
import static myproject.shoppingmall.domain.QMember.member;
import static myproject.shoppingmall.domain.item.QImage.*;
import static myproject.shoppingmall.domain.item.QItem.*;
import static myproject.shoppingmall.domain.order.QDelivery.*;
import static myproject.shoppingmall.domain.order.QOrder.*;
import static myproject.shoppingmall.domain.order.QOrderItem.*;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Order> getMyOrderList(Long memberId, OrderSearch orderSearch, Pageable pageable) {
        List<Order> results = queryFactory
                .select(order).distinct()
                .from(order)
                .join(order.member, member).fetchJoin()
                .join(order.orderItemList, orderItem).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .where(memberIdEq(memberId),
                        orderStatusEq(orderSearch.getOrderStatus()))
                .orderBy(sorter(orderSearch.getOrderSorter()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Order> countQuery = queryFactory
                .selectFrom(order)
                .where(memberIdEq(memberId));

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchCount);

    }
    private OrderSpecifier sorter(OrderSorter sorter) {
        if (sorter == OrderSorter.BYRECENTDATE) {
            return order.createDate.desc();
        } else if (sorter == OrderSorter.BYOLDDATE) {
            return order.createDate.asc();
        }
        return order.createDate.desc();
    }

    private BooleanBuilder memberIdEq(Long memberId) {
        return nullSafeBuilder(() -> order.member.id.eq(memberId));
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
