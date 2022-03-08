package myproject.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.domain.item.ItemSearch;
import myproject.shoppingmall.dto.ItemDto;
import myproject.shoppingmall.dto.ItemSearchDto;
import myproject.shoppingmall.service.CategoryService;
import myproject.shoppingmall.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    @GetMapping("/items")
    public String shopMain(@RequestParam(value = "category", required = false) Long category,
                           @ModelAttribute ItemSearch itemSearch, @PageableDefault(size = 6) Pageable pageable,
                           Model model) {

        model.addAttribute("rootCategory", categoryService.createCategoryRoot());

//        if (itemSearch == null) {
//            model.addAttribute("itemSearch", new ItemSearch());
//        } else {
//            if (itemSearch.getName() == null) {
//
//            }
//            model.addAttribute("itemSearch", itemSearch);
//        }

        itemSearch.setCategoryId(category);
        Page<ItemSearchDto> results = itemService.findAllForSearch(itemSearch, pageable);
        model.addAttribute("items", results.getContent());
        model.addAttribute("totalPage", results.getTotalPages());

        return "shop/shopHome";
    }

    @GetMapping("/items/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId, Model model) {
        ItemDto item = itemService.findItem(itemId);

        model.addAttribute("item", item);

        return "shop/itemDetail";
    }

    @GetMapping("/category")
    public String getCategory(Model model) {
        model.addAttribute("rootCategory", categoryService.createCategoryRoot());

        return "shop/category";
    }
}
