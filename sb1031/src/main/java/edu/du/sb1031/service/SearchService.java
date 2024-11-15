package edu.du.sb1031.service;

import edu.du.sb1031.dto.SearchType;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Wishlist;
import edu.du.sb1031.exception.InvalidSearchTypeException;
import edu.du.sb1031.exception.WishlistNotFoundException;
import edu.du.sb1031.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final WishlistRepository wishlistRepository;

    public List<Item> searchByTypeAndCategories(SearchType searchType, List<Long> categoryIds) {
        if(searchType == null ) searchType = SearchType.MOST_SELL;

        return itemService.findByTypeAndCategory(searchType, categoryService.getSubCategoryIdsFromIds(categoryIds));
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
        }
    }

    public List<Item> searchByWishlistFromMemberId(Long memberId) {
        List<Wishlist> wishlist = wishlistRepository.findByMemberIdOrderByIdDesc(memberId);
        if(wishlist.isEmpty()) throw new WishlistNotFoundException();
        return wishlist.stream().map(Wishlist::getItem).collect(Collectors.toList());
    }

}
