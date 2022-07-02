package myproject.shoppingmall.domain.itemImage.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.itemImage.entity.ItemImage;
import myproject.shoppingmall.domain.itemImage.repository.ItemImageRepository;
import myproject.shoppingmall.infra.FileService;
import myproject.shoppingmall.infra.UploadFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemImageService {

    private final FileService fileService;
    private final ItemImageRepository itemImageRepository;

    public List<ItemImage> saveItemImageList(List<MultipartFile> itemImageFiles) throws IOException {

        List<UploadFile> uploadFiles = fileService.storeFiles(itemImageFiles);

        List<ItemImage> itemImageList = new ArrayList<>();
        for (int i = 0; i < uploadFiles.size(); i++) {
            boolean rep = isFirstIndex(i);
            ItemImage itemImage = uploadFiles.get(i).toEntity(rep);
            itemImageList.add(itemImage);
        }

        return itemImageList;
    }

    private boolean isFirstIndex(int i) {
        return i == 0;
    }

}
