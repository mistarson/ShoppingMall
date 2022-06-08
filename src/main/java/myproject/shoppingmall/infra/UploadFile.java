package myproject.shoppingmall.infra;

import lombok.Getter;
import lombok.Setter;
import myproject.shoppingmall.domain.itemImage.entity.ItemImage;

@Getter @Setter
public class UploadFile {

    private String originalFileName;  // 원본 파일 이름
    private String storeFileName;     // 저장된 파일 이름
    private String fileUploadUrl;     // 파일 저장 경로
    private final String IMAGE_URL_PREFIX = "/images/";

    public UploadFile(String originalFileName, String storeFileName, String fileUploadUrl) {
        this.originalFileName = originalFileName;
        this.storeFileName = storeFileName;
        this.fileUploadUrl = fileUploadUrl;
    }

    public ItemImage toEntity(boolean rep) {
        return ItemImage.builder()
                .imageName(storeFileName)
                .imageUrl(IMAGE_URL_PREFIX + storeFileName)
                .isRepImage(rep)
                .originalImageName(originalFileName)
                .build();
    }


}
