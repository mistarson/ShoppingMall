package myproject.shoppingmall.web.adminItem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.shoppingmall.global.error.exception.BusinessException;
import myproject.shoppingmall.web.adminItem.form.RegisterItemForm;
import myproject.shoppingmall.web.adminItem.service.AdminItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/items")
@Slf4j
public class AdminItemController {

    private final AdminItemService adminItemService;

    @GetMapping("/new")
    public String itemForm(Model model) {

        model.addAttribute("createItemForm", new RegisterItemForm());

        return "adminItem/createItemForm";
    }

    @PostMapping("/new")
    public String createItem(@Valid RegisterItemForm createItemForm, BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "adminItem/createItemForm";
        }

        Long itemId = null;
        try {
            itemId = adminItemService.registerItem(createItemForm);
        } catch (BusinessException | IOException e) {
            e.printStackTrace();
            bindingResult.addError(new ObjectError("createItemForm", e.getMessage()));

            return "adminItem/createItemForm";
        }

        redirectAttributes.addAttribute("itemId", itemId);

        return "redirect:/admin/items/{itemId}";

    }



}
