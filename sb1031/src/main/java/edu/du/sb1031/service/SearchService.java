package edu.du.sb1031.service;

import edu.du.sb1031.dto.SearchType;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.exception.InvalidSearchTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final ItemService itemService;
    private final CategoryService categoryService;

    public List<Item> searchByTypeAndCategories(SearchType searchType, List<Long> categoryIds) {
        if(searchType == null ) searchType = SearchType.MOST_SELL;
        return itemService.findByTypeAndCategory(searchType, categoryService.getSubCategoryIdsFromIds(categoryIds));
    }

}
