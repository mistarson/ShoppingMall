package myproject.shoppingmall.web.adminItem.validator;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.entity.Item;
import myproject.shoppingmall.domain.item.service.ItemService;
import myproject.shoppingmall.global.error.exception.BusinessException;
import myproject.shoppingmall.global.error.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminItemValidator {

    private final ItemService itemService;

    public void validateDuplicateItem(String itemName) {

        Optional<Item> item = itemService.findByItemName(itemName);
        if (item.isPresent()) {
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_ITEM);
        }
    }

    public void isExistRepImage(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new BusinessException(ErrorCode.FIRST_ITEM_IMAGE_NOT_EXISTS);
        }
    }
}
