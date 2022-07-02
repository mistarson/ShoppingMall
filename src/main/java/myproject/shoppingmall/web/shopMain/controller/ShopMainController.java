package myproject.shoppingmall.web.shopMain.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.category.service.CategoryService;
import myproject.shoppingmall.web.shopMain.dto.ItemSearchDto;
import myproject.shoppingmall.web.shopMain.search.ItemSearch;
import myproject.shoppingmall.web.shopMain.service.ShopMainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ShopMainController {

    private final CategoryService categoryService;
    private final ShopMainService shopMainService;

    @GetMapping("/items")
    public String shopMain(@RequestParam(value = "category", required = false) Long category,
                           @ModelAttribute ItemSearch itemSearch, @PageableDefault(size = 6) Pageable pageable,
                           Model model) {

//        model.addAttribute("rootCategory", categoryService.createCategoryRoot());

//        itemSearch.setCategoryId(category);

        if (itemSearch.getName() == null) {
            itemSearch.setName("");
        }
        Page<ItemSearchDto> results = shopMainService.getItemsForSearch(itemSearch, pageable);
        model.addAttribute("items", results.getContent());
        model.addAttribute("totalPage", results.getTotalPages());

        return "shop/shopHome";
    }
}
