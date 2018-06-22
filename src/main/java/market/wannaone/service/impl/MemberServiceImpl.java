package market.wannaone.service.impl;

import market.wannaone.domain.Member;
import market.wannaone.domain.MemberRole;
import market.wannaone.repository.MemberRepository;
import market.wannaone.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        if (id == 10L)
            throw new RuntimeException("id == 10");
        return memberRepository.getMember(id);
    }

    @Override
    @Transactional
    public Member addMember(Member member) {
        MemberRole memberRole = new MemberRole();
        memberRole.setName("USER");
        member.addMemberRole(memberRole);
        member =
                memberRepository.save(member);
        return member;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMemberByEmail(String email) {
        return memberRepository.getMemberByEmail(email);
    }

    @Override
    @Transactional
    public Member modifyMember(Member member) {
        return memberRepository.save(member);
    }
}
