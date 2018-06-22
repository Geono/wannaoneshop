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
    @Transactional
    public Item addItem(
            String name, int price, String info,
            String descriptionImageFilename,
            String representativeImageFilename,
            int totalCount,
            int soldCount) {

        Item item = new Item() {{
            setId(null);
            setName(name);
            setPrice(price);
            setInfo(info);
            setDescriptionImageFilename(descriptionImageFilename);
            setRepresentativeImageFilename(representativeImageFilename);
            setTotalCount(totalCount);
            setSoldCount(soldCount);
        }};

        return itemRepository.save(item);
    }
}