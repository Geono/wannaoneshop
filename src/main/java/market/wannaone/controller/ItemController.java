package market.wannaone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    @GetMapping(path = "/items")
    public String itemlist() {
        return "item/list";
    }

    @GetMapping(path = "/item/add")
    public String addItem() {
        return "item/add";
    }
}
