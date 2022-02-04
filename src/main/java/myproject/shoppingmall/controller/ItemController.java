//package myproject.shoppingmall.controller;
//
//import lombok.RequiredArgsConstructor;
//import myproject.shoppingmall.domain.item.ItemSearch;
//import myproject.shoppingmall.service.CategoryService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequiredArgsConstructor
//public class ItemController {
//
//    private final CategoryService categoryService;
//
//    @GetMapping("/items")
//    public String shopMain(@RequestParam(value = "category", required = false) Long category,
//                           @ModelAttribute ItemSearch itemSearch,
//                           Model model) {
//
//        model.addAttribute("rootCategory", categoryService.createCategoryRoot());
//
//        if (itemSearch == null) {
//            model.addAttribute("itemSearch", new ItemSearch());
//        } else {
//            model.addAttribute("itemSearch", itemSearch);
//        }
//
//        itemSearch.setCategoryId(category);
//
//    }
//}
