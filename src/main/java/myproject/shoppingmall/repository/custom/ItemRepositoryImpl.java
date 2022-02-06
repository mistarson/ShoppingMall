package myproject.shoppingmall.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import myproject.shoppingmall.domain.item.ItemSearch;
import myproject.shoppingmall.domain.item.Sorter;
import myproject.shoppingmall.dto.ItemSearchDto;
import myproject.shoppingmall.dto.QItemSearchDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

import static myproject.shoppingmall.domain.item.QItem.*;

public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ItemSearchDto> findAll(ItemSearch itemSearch) {

        // TODO 이름검색 안했을 때, 메소드 수정해야함  
        if (itemSearch.getName() == "") {
            return queryFactory
                    .select(new QItemSearchDto(item))
                    .from(item)
                    .where(categoryEq(itemSearch.getCategoryId()))
                    .orderBy(sorter(itemSearch.getSorter()))
                    .fetch();
        }
        return queryFactory
                .select(new QItemSearchDto(item))
                .from(item)
                .where(itemNameEq(itemSearch.getName()),
                        categoryEq(itemSearch.getCategoryId()))
                .orderBy(sorter(itemSearch.getSorter()))
                .fetch();
    }

    private OrderSpecifier sorter(Sorter sorter) {

         if (sorter == Sorter.BYPRICE) {
            return item.price.desc();
        } else if (sorter == Sorter.BYDATE) {
            return item.createDate.desc();
        }

        return item.createDate.desc();

    }

    private BooleanBuilder categoryEq(Long categoryId) {
        return nullSafeBuilder(() -> item.categoryId.eq(categoryId));
    }

    private Predicate itemNameEq(String name) {
        return nullSafeBuilder(() -> item.name.eq(name));
    }

    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }
}
