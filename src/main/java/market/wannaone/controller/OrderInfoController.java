package market.wannaone.controller;

import market.wannaone.domain.Cart;
import market.wannaone.domain.Item;
import market.wannaone.domain.Member;
import market.wannaone.domain.OrderInfo;
import market.wannaone.service.CartService;
import market.wannaone.service.ItemService;
import market.wannaone.service.MemberService;
import market.wannaone.service.OrderInfoService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    MemberService memberService;
    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;

    @GetMapping(path = "/addCart/{id}")
    public String addCart(Principal principal, HttpSession session, @ModelAttribute Item item, ModelMap modelMap) {
        // session에 아이템 저장
        List<OrderInfo> orderList = (List<OrderInfo>) session.getAttribute("orderList");
        boolean alreadyIn = false;
        if(orderList == null)
            orderList = new ArrayList<>();
        for( OrderInfo o : orderList) {
            if(o.getItem().getId() == item.getId()) {
                System.out.println("동일 Item 이미 있습니다. 수량을 늘려주세요.");
                alreadyIn = true;
                break;
            }
        }
        if(!alreadyIn) {
            OrderInfo o = makeOrderInfo(memberService.getMemberByEmail(principal.getName()), null, item.getId());
            orderList.add(o);
        }
        session.setAttribute("orderList", orderList);
        modelMap.addAttribute("orders", orderList);
        return "redirect:/order/cart"; // 카트 보여주기
    }

    @GetMapping(path = "cart")
    public String cart(HttpSession session, ModelMap modelMap) {
        List<OrderInfo> orderList = (List<OrderInfo>) session.getAttribute("orderList");
        modelMap.addAttribute("orders", orderList);
        return "order/cart";
    }

    @GetMapping("/list")
    public String list(Principal principal, ModelMap modelMap) {
        Member member = memberService.getMemberByEmail(principal.getName());
        modelMap.addAttribute("orders", orderInfoService.getOrdersByMemberId(member.getId()));
        return "order/list"; // 주문결과 페이지로 리다이렉트
    }

    private OrderInfo makeOrderInfo(Member member, Cart cart, Long itemId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setMember(member);
        orderInfo.setCart(cart);
        orderInfo.setItem(itemService.getItem(itemId));
        orderInfo.setCount(1);
        orderInfo.setStatus("입금대기");
        return orderInfo;
    }

    @PostMapping // POST필요
    public String order(Principal principal, @ModelAttribute Item item) { // just need Id
        Member member = memberService.getMemberByEmail(principal.getName());
        Cart cart = cartService.getNewCartId();
        OrderInfo orderInfo = makeOrderInfo(member, cart, item.getId());
        orderInfoService.addOrderInfo(orderInfo);
        return "redirect:/order/list"; // 주문목록 페이지로 리다이렉트
    }

    @PostMapping("/cart") // POST필요
    public String addCart(Principal principal, HttpSession session) {
        // session 에 있는 것들 loop로 생성
        Member member = memberService.getMemberByEmail(principal.getName());
        Cart cart = cartService.getNewCartId();
        List<OrderInfo> orderList = (List<OrderInfo>) session.getAttribute("orderList");

        if(orderList != null && orderList.size() != 0) {
            for (OrderInfo o : orderList) {
                OrderInfo orderInfo = makeOrderInfo(member, cart, o.getItem().getId()); // refactoring 필요
                orderInfoService.addOrderInfo(orderInfo);
            }
            orderList.clear();
            session.setAttribute("orderList", orderList);
        }
        return "redirect:/order/list"; // 주문목록 페이지로 리다이렉트
    }
}
