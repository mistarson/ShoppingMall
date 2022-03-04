package myproject.shoppingmall.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import myproject.shoppingmall.domain.item.Item;
import myproject.shoppingmall.domain.order.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

import static myproject.shoppingmall.domain.QMember.member;
import static myproject.shoppingmall.domain.item.QImage.*;
import static myproject.shoppingmall.domain.item.QItem.*;
import static myproject.shoppingmall.domain.order.QDelivery.*;
import static myproject.shoppingmall.domain.order.QOrder.*;
import static myproject.shoppingmall.domain.order.QOrderItem.*;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    //Dto로 가져오게 되면 식별자가 없어 JPA의 distinct기능을 사용하지 못해 어쩔 수 없이 엔티티로 받아옴
    @Override
    public Page<Order> getMyOrderList(Long memberId, OrderSearch orderSearch, Pageable pageable) {
        List<Order> content = queryFactory
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

        List<Item> itemCollect = queryFactory
                .selectFrom(item)
                .join(item.imageList, image).fetchJoin()
                .where(item.id.in(JPAExpressions.select(orderItem.item.id)
                        .from(orderItem)
                        .join(orderItem.order, order)
                        .where(memberIdEq(memberId))
                ))
                .fetch();

        JPAQuery<Order> countQuery = queryFactory
                .selectFrom(order)
                .where(memberIdEq(memberId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);

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
