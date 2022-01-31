package myproject.shoppingmall.domain.item;

import javax.persistence.Entity;

@Entity
public class Clothes extends Item{

    ClothesType clothesType;
    ClothesSize clothesSize;
}
