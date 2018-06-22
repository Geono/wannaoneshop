package market.wannaone.service.impl;

import market.wannaone.domain.Item;
import market.wannaone.repository.ItemRepository;
import market.wannaone.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Item> getItems(int page, int size) {
        return itemRepository.findAll(new PageRequest(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public Item getItem(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.get();
    }

    @Override
    @Transactional
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }
}
