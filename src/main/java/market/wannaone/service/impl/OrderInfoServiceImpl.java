package market.wannaone.service.impl;

import market.wannaone.domain.OrderInfo;
import market.wannaone.repository.OrderInfoRepository;
import market.wannaone.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OrderInfo> getOrdersByMemberId(Long memberId) {
        return orderInfoRepository.findAllByMemberId(memberId);
    }

    @Override
    @Transactional
    public OrderInfo addOrderInfo(OrderInfo orderInfo) {
        return orderInfoRepository.save(orderInfo);
    }

    @Override
    @Transactional
    public List<OrderInfo> findOrderInfoByCondition(Long memberId, String status) {
        return orderInfoRepository.findAllByItemMemberId(memberId, status);
    }
}
