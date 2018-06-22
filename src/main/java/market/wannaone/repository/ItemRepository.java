package market.wannaone.repository;

import market.wannaone.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository
        extends JpaRepository<Member, Long> {
}
