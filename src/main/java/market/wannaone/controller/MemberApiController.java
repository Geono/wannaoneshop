package market.wannaone.controller;

import market.wannaone.domain.Member;
import market.wannaone.dto.MyHeader;
import market.wannaone.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberApiController {
    @Autowired
    MemberService memberService;

    @GetMapping
    public List<Member> getMembers(){
        return memberService.getMembers();
    }

    //http://localhost:8080/api/members/1
    @GetMapping(path = "/{id}")
    public Member getMember(MyHeader myHeader,
                            @PathVariable(name="id") Long id){
        System.out.println(myHeader.getAccept());
        System.out.println(myHeader.getHost());
        return memberService.getMember(id);
    }
}
