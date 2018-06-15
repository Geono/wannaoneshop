package market.wannaone.repository;

import market.wannaone.domain.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository
        extends JpaRepository<MemberRole, Long> {

}
