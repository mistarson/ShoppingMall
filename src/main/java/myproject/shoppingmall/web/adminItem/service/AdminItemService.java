package myproject.shoppingmall.web.adminItem.service;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.item.service.ItemService;
import myproject.shoppingmall.domain.itemImage.entity.ItemImage;
import myproject.shoppingmall.domain.itemImage.service.ItemImageService;
import myproject.shoppingmall.web.adminItem.form.RegisterItemForm;
import myproject.shoppingmall.web.adminItem.validator.AdminItemValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminItemService {

    private final AdminItemValidator adminItemValidator;
    private final ItemService itemService;
    private final ItemImageService itemImageService;

    @Transactional
    public Long registerItem(RegisterItemForm registerItemForm) throws IOException {

        validateItem(registerItemForm);

        Item item = registerItemForm.toEntity();
        List<ItemImage> itemImageList = itemImageService.saveItemImageList(registerItemForm.getItemImageFiles());

        Item saveItem = Item.createItem(item, itemImageList);

        return itemService.registerItem(saveItem);
    }

    private void validateItem(RegisterItemForm registerItemForm) {
        adminItemValidator.validateDuplicateItem(registerItemForm.getItemName());
        adminItemValidator.isExistRepImage(registerItemForm.getItemImageFiles().get(0));
    }
}
