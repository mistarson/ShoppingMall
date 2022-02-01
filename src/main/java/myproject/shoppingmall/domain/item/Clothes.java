package myproject.shoppingmall.domain.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("C")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Clothes extends Item {

    ClothesSize clothesSize;

    @Builder
    public Clothes(String name, int price, int stockQuantity, String color, ClothesType clothesType, Long categoryId, ClothesSize clothesSize) {

        super(name, price, stockQuantity, color, clothesType, categoryId);
        this.clothesSize = clothesSize;
    }
}
