package market.wannaone.service;

import market.wannaone.domain.OrderInfo;

import java.util.List;

public interface OrderInfoService {
    public OrderInfo addOrderInfo(OrderInfo orderInfo);
    public List<OrderInfo> getOrderInfoByMemberId(Long member_id);
}
