package edu.du.sb1031.service;


import edu.du.sb1031.dto.SearchType;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.exception.CategoryNotFoundException;
import edu.du.sb1031.exception.InvalidSearchTypeException;
import edu.du.sb1031.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void save(Item item) {
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

    public List<Item> findByTypeAndCategory(SearchType searchType, List<Long> categoryIds) {
        switch (searchType) {
            case RECOMMEND:
                return itemRepository.findByOrderByTotalRating(categoryIds);
            case MOST_SELL:
                return itemRepository.findByOrderBySellDesc(categoryIds);
            case LOW_PRICE:
                return itemRepository.findByOrderByPriceAsc(categoryIds);
            case HIGH_PRICE:
                return itemRepository.findByOrderByPriceDesc(categoryIds);
            case NEWEST:
                return itemRepository.findByOrderByCreateDateTimeDesc(categoryIds);
            case MOST_REVIEWED:
                return itemRepository.findByOrderByTotalReview(categoryIds);
            default:
                throw new InvalidSearchTypeException();
        }//searchService로 옵ㄻ겨야함

    }
}
