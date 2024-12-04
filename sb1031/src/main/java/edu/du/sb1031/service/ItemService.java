package edu.du.sb1031.service;


import edu.du.sb1031.dto.SearchType;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.exception.CategoryNotFoundException;
import edu.du.sb1031.exception.InvalidSearchTypeException;
import edu.du.sb1031.exception.NotEnoughStockException;
import edu.du.sb1031.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void save(Item item){
        if(item.getId() == null&&item.getCreateDateTime() == null) item.setCreateDateTime(LocalDateTime.now());
        itemRepository.save(item);
    }

    public void save(String name, int price, int stock, String company, String imageName, Category category, String content) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setStock(stock);
        item.setCompany(company);
        item.setImageName(imageName);
        item.setCategory(category);
        item.setContent(content);
        item.setCreateDateTime(LocalDateTime.now());
        itemRepository.save(item);
    }

    public void save(String name, int price, int stock, String company, String imageName, Category category) {
        save(name, price, stock, company, imageName, category, "");
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public void setQuantity(Item item, Integer quantity) {

    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findByNameLike(String name) {
        return itemRepository.findByNameLike(name);
    }

    public Item subStock(Item item, int quantity){
        item.setStock(item.getStock()-quantity);
        if(item.getStock()<0) throw new NotEnoughStockException();
        return itemRepository.save(item);
    }
}
