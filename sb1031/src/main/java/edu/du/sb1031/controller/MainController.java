package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.dto.SearchType;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.service.CategoryService;
import edu.du.sb1031.service.ItemService;
import edu.du.sb1031.service.SearchService;
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
//
//    @ModelAttribute("authInfo")
//    public AuthInfo authInfo() {
//        System.out.println("멤버 번호 저장"); // 임시코드
//        return new AuthInfo(2L);
//    }

    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String mainPage(HttpServletRequest request, Model model) {
        List<Category> categories = categoryService.findLimitedChildren();
        model.addAttribute("categories", categories);
        return "/main/main";
    }

    @GetMapping("/search")
    public String recommend(Model model, @RequestParam(name = "type") String searchType, @ModelAttribute(name = "categoryId") Long categoryId) {
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
    public String searchByWishlist(Model model, @SessionAttribute("authInfo") AuthInfo authInfo) {
        Long memberId = authInfo.getId();
        List<Item> items = searchService.searchByWishlistFromMemberId(memberId);//널일 수 있음
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

