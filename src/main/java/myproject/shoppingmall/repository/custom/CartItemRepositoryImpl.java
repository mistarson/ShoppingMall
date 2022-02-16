package myproject.shoppingmall.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import myproject.shoppingmall.domain.cart.QCart;
import myproject.shoppingmall.domain.item.QImage;
import myproject.shoppingmall.domain.item.QItem;
import myproject.shoppingmall.dto.CartItemDto;
import myproject.shoppingmall.dto.QCartItemDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

import static myproject.shoppingmall.domain.cart.QCart.*;
import static myproject.shoppingmall.domain.cart.QCartItem.*;
import static myproject.shoppingmall.domain.item.QImage.*;
import static myproject.shoppingmall.domain.item.QItem.*;

public class CartItemRepositoryImpl implements CartItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CartItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CartItemDto> findAllCartItem(Long memberId) {
        return queryFactory
                .select(new QCartItemDto(item.id, image.imagePath, item.name, item.price, cartItem.orderQuantity, item.stockQuantity))
                .from(cartItem)
                .join(item).on(itemIdEq(item.id))
                .join(image).on(itemIdEq(image.item.id))
                .where(cartIdEq(
                        JPAExpressions
                                .select(cart.id)
                                .from(cart)
                                .where(cart.member.id.eq(memberId))
                ).and(image.imagePath.like("%main%")))
                .fetch();
    }

    private BooleanBuilder itemIdEq(NumberPath<Long> itemId) {
        return nullSafeBuilder(() -> cartItem.itemId.eq(itemId));
    }

    private BooleanBuilder cartIdEq(JPQLQuery<Long> cartId) {
        return nullSafeBuilder(() -> cartItem.cart.id.eq(cartId));
    }


    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }
}
