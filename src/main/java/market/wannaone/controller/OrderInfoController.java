package market.wannaone.controller;

import market.wannaone.domain.Cart;
import market.wannaone.domain.Item;
import market.wannaone.domain.Member;
import market.wannaone.domain.OrderInfo;
import market.wannaone.service.CartService;
import market.wannaone.service.ItemService;
import market.wannaone.service.MemberService;
import market.wannaone.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public String addCart() {
        // session에 아이템 저장
        return "cart"; // 카트 보여주기
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

    @GetMapping("/{id}") // POST필요
    public String order(Principal principal, @PathVariable(name="id") Long itemId) {
        Member member = memberService.getMemberByEmail(principal.getName());
        Cart cart = cartService.getNewCartId();
        OrderInfo orderInfo = makeOrderInfo(member, cart, itemId);
        orderInfoService.addOrderInfo(orderInfo);
        return "list"; // 주문결과 페이지로 리다이렉트
    }

    @GetMapping("/cart") // POST필요
    public String order(Principal principal, @ModelAttribute Item item) {
        // session 에 있는 것들 loop로 생성
        Member member = memberService.getMemberByEmail(principal.getName());
        Cart cart = cartService.getNewCartId();
        //for
//        OrderInfo orderInfo = makeOrderInfo(member, cart, itemId);
//        orderInfoService.addOrderInfo(orderInfo);
        return "list"; // 주문결과 페이지로 리다이렉트
    }
}
