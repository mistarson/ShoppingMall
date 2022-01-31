package myproject.shoppingmall.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Long categoryId;
}
