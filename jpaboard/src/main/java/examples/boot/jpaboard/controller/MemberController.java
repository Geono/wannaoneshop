package examples.boot.jpaboard.controller;

import examples.boot.jpaboard.domain.Member;
import examples.boot.jpaboard.domain.MemberRole;
import examples.boot.jpaboard.service.MemberService;
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
        System.out.println(principal.getName());
        // modelMap.addAttribute("member", member);
        return "members/profile";
    }

    @GetMapping(path = "/joinform")
    public String joinform(){
        return "members/joinform";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member) {
        member.setRegdate(LocalDateTime.now());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(member.getPasswd());
        member.setPasswd(passwordEncoder.encode(member.getPasswd()));

        member = memberService.addMember(member);
        System.out.println(member.getId());
        System.out.println(member.getName());

        return "redirect:/members/welcome";
    }

    @GetMapping(path="/welcome")
    public String welcome(){
        return "members/welcome";
    }
}
