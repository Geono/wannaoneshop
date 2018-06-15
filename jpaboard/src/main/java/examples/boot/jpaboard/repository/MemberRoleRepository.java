package examples.boot.jpaboard.repository;

import examples.boot.jpaboard.domain.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository
        extends JpaRepository<MemberRole, Long> {

}
