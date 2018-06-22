package market.wannaone.service;

import market.wannaone.domain.Item;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {
    public Page<Item> getItems(int page, int size);
    public Item getItem(Long id);

    public Item addItem(
            String name, int price, String info,
            String descriptionImageFilename,
            String representativeImageFilename,
            int totalCount,
            int soldCount);
}
