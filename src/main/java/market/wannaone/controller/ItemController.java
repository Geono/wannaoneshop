package market.wannaone.controller;

import market.wannaone.domain.Item;
import market.wannaone.service.ItemService;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    private static final int PAGE_SIZE = 5;
    @Autowired
    ItemService itemService;

    @GetMapping(path = "/list")
    public String listItems(Model model, @RequestParam int page) {
        List<Item> items = itemService.getItems(page - 1, PAGE_SIZE).getContent();
        model.addAttribute("items", items);
        return "item/list";
    }

    @GetMapping(path = "/add")
    public String addItem() {
        return "item/add";
    }
}
