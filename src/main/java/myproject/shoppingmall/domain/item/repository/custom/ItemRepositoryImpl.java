package myproject.shoppingmall.domain.item.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.querydsl.DynamicQuery;
import myproject.shoppingmall.web.shopMain.search.ItemSearch;
import myproject.shoppingmall.web.shopMain.search.ItemSorter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static myproject.shoppingmall.domain.item.entity.QItem.item;
import static myproject.shoppingmall.domain.itemImage.entity.QItemImage.itemImage;


@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Item> findAllWithSearch(ItemSearch itemSearch, Pageable pageable) {

        List<Item> content = queryFactory
                .selectFrom(item)
                .join(item.itemImageList, itemImage).fetchJoin()
                .where(itemNameLike(itemSearch.getName()))
                .orderBy(sorter(itemSearch.getSorter()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Item> countQuery = queryFactory
                .select(item)
                .from(item)
                .where(itemNameLike(itemSearch.getName()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
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
        return DynamicQuery.nullSafeBuilder(() -> item.categoryId.eq(categoryId));
    }

    private BooleanBuilder itemNameLike(String name) {
        return DynamicQuery.nullSafeBuilder(() -> item.itemName.like("%" + name + "%"));
    }
}
