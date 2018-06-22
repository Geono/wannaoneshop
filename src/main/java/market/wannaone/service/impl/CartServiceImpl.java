package market.wannaone.service.impl;

import market.wannaone.domain.Cart;
import market.wannaone.repository.CartRepository;
import market.wannaone.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    @Transactional(readOnly = true)
    public Cart getNewCartId() {
        Cart cart = new Cart();
        cart = cartRepository.save(cart);
        return cart;
    }
}
