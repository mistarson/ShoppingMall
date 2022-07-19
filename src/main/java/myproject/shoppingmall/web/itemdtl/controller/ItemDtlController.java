package myproject.shoppingmall.web.itemdtl.controller;

import lombok.RequiredArgsConstructor;
import myproject.shoppingmall.web.dto.ItemDtlDto;
import myproject.shoppingmall.web.itemdtl.service.ItemDtlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ItemDtlController {

    private final ItemDtlService itemDtlService;

    @GetMapping("/items/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId, Model model) {

        ItemDtlDto itemDto = itemDtlService.getItemDetail(itemId);

        model.addAttribute("itemDto", itemDto);

        return "shop/itemDetail";
    }

}
