package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.dto.SearchType;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.service.CategoryService;
import edu.du.sb1031.service.ItemService;
import edu.du.sb1031.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("selectedCategory")
public class SearchController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final SearchService searchService;

    @GetMapping("/search/{str}")
    public String search(@PathVariable String str, HttpServletRequest request, Model model) {
        if (!model.containsAttribute("selectedCategory")) {
            model.addAttribute("selectedCategory", 1L); // 기본 카테고리 "all"로 설정
        }

        str = '%'+str+'%';
        List<Item> items = itemService.findByNameLike(str);
        List<Category> categories = categoryService.getSubCategories(categoryService.findAll());
        model.addAttribute("categories", categories);
        model.addAttribute("items", items);
        return "/search/search";
    }

    @GetMapping("/search")
    public String recommend(Model model, @RequestParam(name = "type") String searchType, @ModelAttribute(name = "selectedCategory") List<Long> categoryIds) {
        if (!model.containsAttribute("selectedCategory")) {
            model.addAttribute("selectedCategory", 1L); // 기본 카테고리 "all"로 설정
        }

        List<Category> categories = categoryService.getSubCategories(categoryService.findAll());
        model.addAttribute("categories", categories);
        System.out.println(categoryIds);
        List<Item> items = itemService.findByTypeAndCategory(SearchType.fromValue(searchType), categoryService.getSubCategoryIdsFromIds(categoryIds));
        List<Item> items1 = searchService.searchByTypeAndCategories(SearchType.fromValue(searchType), categoryService.getSubCategoryIdsFromIds(categoryIds));
        model.addAttribute("items", items);
        return "/search/search";
    }

    @GetMapping("/search/wishlist")
    public String searchByWishlist(Model model, @SessionAttribute("authInfo") AuthInfo authInfo) {
        Long memberId = authInfo.getId();
        List<Item> items = searchService.searchByWishlistFromMemberId(memberId);
        model.addAttribute("items", items);
        return "/search/search";
    }

//    난 카테고리를 리스트로 받을 줄 알고 이렇게 짰어..
//    @GetMapping("/search")
//    public String recommend(Model model, @RequestParam(name = "type") String searchType, @RequestParam(name = "categoryId", required = false) List<Long> categoryIds) {
//        if (!model.containsAttribute("selectedCategory")) {
//            model.addAttribute("selectedCategory", 1L); // 기본 카테고리 "all"로 설정
//        }
//
//        List<Category> categories = categoryService.getSubCategories(categoryService.findAll());
//        model.addAttribute("categories", categories);
//        System.out.println(categoryIds);
//        List<Item> items = itemService.findByTypeAndCategory(SearchType.fromValue(searchType), categoryService.getSubCategoryIdsFromIds(categoryIds));
//        model.addAttribute("items", items);
//        return "/search/search";
//    }
}
