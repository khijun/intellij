package edu.du.sb1031.service;


import edu.du.sb1031.entity.Item;
import edu.du.sb1031.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public void setQuantity(Item item, Integer quantity) {

    }

    public List<Item> findAll() {return itemRepository.findAll();}

    public List<Item> findByNameLike(String name) {
        return itemRepository.findByNameLike(name);
    }
}
