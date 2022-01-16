package myproject.shoppingmall.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NoArgsConstructor
public class CommunityController {

    @GetMapping("/community")
    public String list() {
        return "community/postList";
    }
}
