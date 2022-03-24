package myproject.shoppingmall.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import myproject.shoppingmall.domain.item.ItemSearch;
import myproject.shoppingmall.domain.item.ItemSorter;
import myproject.shoppingmall.dto.ItemSearchDto;
import myproject.shoppingmall.dto.QItemSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

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
    public Page<ItemSearchDto> findAll(ItemSearch itemSearch, Pageable pageable) {

        QueryResults<ItemSearchDto> results;

        // TODO 이름검색 안했을 때, 메소드 수정해야함
        if (itemSearch.getName() == "") {
            results = queryFactory
                    .select(new QItemSearchDto(item))
                    .from(item)
                    .where(categoryEq(itemSearch.getCategoryId()))
                    .orderBy(sorter(itemSearch.getSorter()))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();

        } else {
            results = queryFactory
                    .select(new QItemSearchDto(item))
                    .from(item)
                    .where(itemNameEq(itemSearch.getName()),
                            categoryEq(itemSearch.getCategoryId()))
                    .orderBy(sorter(itemSearch.getSorter()))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
        }
        List<ItemSearchDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);

    }

    private OrderSpecifier sorter(ItemSorter sorter) {

         if (sorter == ItemSorter.BYPRICE) {
            return item.price.desc();
        } else if (sorter == ItemSorter.BYDATE) {
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
