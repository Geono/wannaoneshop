package market.wannaone.repository;

import market.wannaone.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

    @Query("select distinct o from OrderInfo o where o.member.id=:id")
    public List<OrderInfo> getOrderInfoByMemberId(@Param("id") Long id);
}
