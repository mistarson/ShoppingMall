package myproject.shoppingmall.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import myproject.shoppingmall.domain.cart.Cart;

import javax.persistence.EntityManager;
import java.util.function.Supplier;

import static myproject.shoppingmall.domain.QMember.*;
import static myproject.shoppingmall.domain.cart.QCart.*;

public class CartRepositoryImpl implements CartRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CartRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Cart findByMemberId(Long memberId) {
        return queryFactory
                .selectFrom(cart)
                .leftJoin(cart.member, member)
                .where(memberIdEq(memberId))
                .fetchOne();
    }

    private BooleanBuilder memberIdEq(Long memberId) {
        return nullSafeBuilder(() -> member.id.eq(memberId));
    }

    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }
}
