package myproject.shoppingmall.domain.itemImage.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myproject.shoppingmall.domain.item.entity.Item;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(length = 500)
    private String imageName;

    @Column(length = 500)
    private String imageUrl;

    private boolean isRepImage;

    @Column(length = 200)
    private String originalImageName;

    @Builder
    public ItemImage(String imageName, String imageUrl, boolean isRepImage, String originalImageName) {
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.isRepImage = isRepImage;
        this.originalImageName = originalImageName;
    }

    public void addItem(Item item) {
        this.item = item;
    }
}
