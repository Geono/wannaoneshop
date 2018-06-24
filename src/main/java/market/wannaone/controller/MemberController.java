package market.wannaone.controller;

import market.wannaone.domain.Member;
import market.wannaone.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping(path = "/profile")
    public String profile(Principal principal, ModelMap modelMap) {
        Member member = memberService.getMemberByEmail(principal.getName());
        modelMap.addAttribute("member", member);
        return "members/profile";
    }

    @GetMapping(path = "/modifyform")
    public String modifyform(Principal principal, ModelMap modelMap) {
        Member member = memberService.getMemberByEmail(principal.getName());
        modelMap.addAttribute("member", member);
        return "members/modifyform";
    }

    @PostMapping(path = "/modify")
    public String modifyform(Principal principal, @ModelAttribute Member modifiedMember) {
        Member member = memberService.getMemberByEmail(principal.getName());
        if(!modifiedMember.getPassword().isEmpty()) {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            member.setPassword(passwordEncoder.encode(modifiedMember.getPassword()));
        }
        if(!modifiedMember.getAddress().isEmpty())
            member.setAddress(modifiedMember.getAddress());
        if(!modifiedMember.getMobile().isEmpty())
            member.setMobile(modifiedMember.getMobile());

        memberService.modifyMember(member);
        return "redirect:/members/profile";
    }

    @GetMapping(path = "/joinform")
    public String joinform(){
        return "members/joinform";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member) {
        member.setRegdate(LocalDateTime.now());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(member.getPassword());
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        member = memberService.addMember(member);
        System.out.println(member.getId());
        System.out.println(member.getName());

        return "redirect:/members/login";
    }

    @GetMapping(path="/welcome")
    public String welcome(){
        return "members/welcome";
    }

    @GetMapping(path="/login")
    public String login() {
        return "members/login";
    }
}
