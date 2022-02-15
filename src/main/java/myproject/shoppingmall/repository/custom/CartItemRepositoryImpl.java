package myproject.shoppingmall.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import myproject.shoppingmall.dto.CartItemDto;
import myproject.shoppingmall.dto.QCartItemDto;

import javax.persistence.EntityManager;
import java.util.List;

public class CartItemRepositoryImpl implements CartItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CartItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CartItemDto> findAllCartItem(Long memberId) {
        return queryFactory
                //멤버아이디와 같은 카트의 카트아이템들을 꺼내와야함

    }
}
