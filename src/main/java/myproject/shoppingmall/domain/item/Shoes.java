//package myproject.shoppingmall.domain.item;
//
//
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.DiscriminatorValue;
//import javax.persistence.Entity;
//
//@Entity
//@Getter
//@DiscriminatorValue("S")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Shoes extends Item {
//
//    int shoesSize;
//
//    @Builder
//    public Shoes(String name, int price, int stockQuantity, String color, ClothesType clothesType, Long categoryId, int shoesSize) {
//        super(name, price, stockQuantity, color, clothesType, categoryId);
//        this.shoesSize = shoesSize;
//    }
//}
