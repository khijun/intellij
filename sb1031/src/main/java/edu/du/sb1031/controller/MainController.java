package edu.du.sb1031.controller;

import edu.du.sb1031.dto.SearchType;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private SearchService searchService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthService authService;
    @Autowired
    private MainPageItemsService mainPageItemsService;

    @GetMapping("/")
    public String mainPage(HttpServletRequest request, Model model) {
        List<Category> categories = categoryService.findLimitedChildren();
        model.addAttribute("categories", categories);
        return "/main/main";
    }
    @GetMapping("/124")
    public String mainPage2(HttpServletRequest request, Model model) {
        List<Category> categories = categoryService.findLimitedChildren();
        model.addAttribute("mainPageData", mainPageItemsService.getMainPageData());
        return "/main/main_old";
    }

    @GetMapping("/search")
    public String recommend(Model model, @RequestParam(name = "type", required = false) String searchType, @ModelAttribute(name = "categoryId") Long categoryId) {
        if (!model.containsAttribute("categoryId")) {
            model.addAttribute("selectedCategory", 1L); // 기본 카테고리 "all"로 설정
        }else{
            model.addAttribute("selectedCategory", categoryId);
        }

        List<Category> categories = categoryService.getSubCategories(categoryService.findAll());
        model.addAttribute("categories", categories);
        List<Item> items = searchService.searchByTypeAndCategories(SearchType.fromValue(searchType), categoryService.getSubCategoryIdsFromIds(Collections.singletonList(categoryId)));
        model.addAttribute("items", items);
        return "/search/search";
    }

    @GetMapping({"/searchName/{str}", "/searchName"})
    public String search(@PathVariable(required = false) String str, HttpServletRequest request, Model model) {
        if (!model.containsAttribute("selectedCategory")) {
            model.addAttribute("selectedCategory", 1L); // 기본 카테고리 "all"로 설정
        }
        str = str==null?"%%":'%'+str+'%';
        List<Item> items = itemService.findByNameLike(str);
        List<Category> categories = categoryService.getSubCategories(categoryService.findAll());
        model.addAttribute("categories", categories);
        model.addAttribute("items", items);
        return "/search/search";
    }

    @GetMapping("/search/wishlist")
    public String searchByWishlist(Model model) {
        Member member = authService.getCurrentMember();
        List<Item> items = searchService.searchByWishlistFromMemberId(member.getId());//널일 수 있음
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
//        List<Item> items = itemService.findByTypeAndCategory(SearchType.fromValue(searchType), categoryService.getSubCategoryIdsFromIds(categoryIds));
//        model.addAttribute("items", items);
//        return "/search/search";
//    }

}

