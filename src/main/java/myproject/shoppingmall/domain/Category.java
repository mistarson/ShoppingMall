package myproject.shoppingmall.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id", unique = true, nullable = false)
    private Long id;

    private String categoryName;

    private Long parentId;

    @Builder
    public Category(String categoryName, Long parentId) {
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
