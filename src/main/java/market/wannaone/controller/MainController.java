package market.wannaone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // resources/templates/main.html;
    @GetMapping(path="/")
    public String main() {
        return "main";
    }
}
