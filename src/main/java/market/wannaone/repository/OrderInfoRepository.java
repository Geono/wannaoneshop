package market.wannaone.repository;

import market.wannaone.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
    @Query("select distinct o from OrderInfo o join fetch o.member join fetch o.cart join fetch o.item where o.member.id=:id order by o.cart.id desc")
    public List<OrderInfo> findAllByMemberId(@Param("id") Long memberId);

    @Query("select distinct o from OrderInfo o join fetch o.member join fetch o.cart join fetch o.item where o.item.member.id=:id and o.status = :status order by o.cart.id desc")
    public List<OrderInfo> findAllByItemMemberId(@Param("id") Long memberId, @Param("status") String status);
}
