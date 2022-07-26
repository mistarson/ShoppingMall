package myproject.shoppingmall.domain.cartItem.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import myproject.shoppingmall.domain.cartItem.entity.CartItem;
import myproject.shoppingmall.domain.item.entity.QItem;
import myproject.shoppingmall.web.dto.CartItemDto;
import myproject.shoppingmall.web.dto.QCartItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static myproject.shoppingmall.domain.cart.entity.QCart.cart;
import static myproject.shoppingmall.domain.cartItem.entity.QCartItem.cartItem;
import static myproject.shoppingmall.domain.item.entity.QItem.item;
import static myproject.shoppingmall.domain.itemImage.entity.QItemImage.itemImage;


public class CartItemRepositoryImpl implements CartItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CartItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<CartItemDto> findAllCartItemForUser(Long memberId, Pageable pageable) {
        List<CartItemDto> content = queryFactory
                .select(new QCartItemDto(item.id, itemImage.imageUrl, item.itemName, item.price, cartItem.orderQuantity, item.stockQuantity))
                .from(cartItem)
                .join(item).on(itemIdEquals(cartItem.itemId))
                .join(itemImage).on(itemEquals())
                .where(cartIdEq(
                        JPAExpressions
                                .select(cart.id)
                                .from(cart)
                                .where(memberIdEquals(memberId)))
                ,itemImage.isRepImage.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<CartItem> countQuery = queryFactory
                .selectFrom(cartItem)
                .where(cartIdEq(
                        JPAExpressions
                                .select(cart.id)
                                .from(cart)
                                .where(memberIdEquals(memberId))
                ));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);

    }

    private BooleanExpression itemIdEquals(NumberPath<Long> itemId) {
        return item.id.eq(itemId);
    }

    private BooleanExpression cartIdEq(JPQLQuery<Long> cartId) {
        return cartItem.cart.id.eq(cartId);
    }

    private BooleanExpression itemEquals() {
        return itemImage.item.eq(QItem.item);
    }

    private BooleanExpression memberIdEquals(Long memberId) {
        return cart.member.id.eq(memberId);
    }


}
