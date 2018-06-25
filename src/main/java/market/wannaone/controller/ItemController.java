package market.wannaone.controller;

import market.wannaone.domain.Item;
import market.wannaone.service.ItemService;
import market.wannaone.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    private static final int PAGE_SIZE = 10;
    @Autowired
    ItemService itemService;
    @Autowired
    MemberService memberService;

    @GetMapping(path = "/list")
    public String listItems(HttpServletRequest request, Model model, @RequestParam(required = false) Integer page) {
        if(page == null)
            return "forward:/item/list?page=1";
//        List<Item> items = itemService.getItems(page - 1, PAGE_SIZE).getContent();
//        long count = itemService.getCount();
//        int numOfPages = (int) Math.ceil( (count - 1) / PAGE_SIZE ) + 1;
//        int startPageNo = numOfPages - ((page / 10) * 10);

        page = 1;
        long count = 60;
        int numOfPages = (int) Math.ceil( (count - 1) / PAGE_SIZE ) + 1;
        int startPageNo = numOfPages - ((page / 10) * 10);

        System.out.println("startPageNo: " + startPageNo);
        System.out.println("numOfPages: " + numOfPages);
        System.out.println("currentPage: " + page);
        System.out.println("threshold: " + ((count - 1) / 10) * 10);
        long threshold = ((count - 1) / 10) * 10;
        List<Integer> pageNos = new ArrayList();
        for(int i = startPageNo; i <= numOfPages; i++)
            pageNos.add(i);
//        model.addAttribute("items", items);
        model.addAttribute("count", count);
        model.addAttribute("pages", pageNos);
        model.addAttribute("currentPage", (int) page);
        model.addAttribute("nextDisabled", page >= threshold);
        return "item/list";
    }

    @GetMapping(path = "/add")
    public String addItem(HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println("currentUserName: " + currentUserName);
            return "item/add";
        } else {
            try {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('로그인 정보를 확인해주세요.'); history.go(-1);</script>");
                out.flush();
                return "item/list";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return "item/list";
            }
        }
    }

    @PostMapping("/add")
    public String submitItem(@ModelAttribute Item item) {
        System.out.println("Name: " + item.getName());
        System.out.println("Price: " + item.getPrice());
        System.out.println("Info: " + item.getInfo());
        System.out.println("TotalCount: " + item.getTotalCount());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        item.setMember(memberService.getMemberByEmail(currentUserName));
        System.out.println("TotalCount: " + item.getTotalCount());

        itemService.addItem(item);
        return "item/addresult";
    }
}
