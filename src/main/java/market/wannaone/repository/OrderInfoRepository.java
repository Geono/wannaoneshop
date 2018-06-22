package market.wannaone.repository;

import market.wannaone.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
}
