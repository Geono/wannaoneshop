package market.wannaone.service;

import market.wannaone.domain.OrderInfo;

import java.util.List;

public interface OrderInfoService {
    public List<OrderInfo> getOrdersByMemberId(Long memberId);
    public OrderInfo addOrderInfo(OrderInfo orderInfo);
}
