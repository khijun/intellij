package edu.du.sb1031;

import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.repository.ItemRepository;
import edu.du.sb1031.service.CartService;
import edu.du.sb1031.service.CategoryService;
import edu.du.sb1031.service.ItemService;
import edu.du.sb1031.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Sb1031ApplicationTests {

    @Autowired
    MemberService memberService;
    @Autowired
    CartService cartService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @Test
    void contextLoads() {
        Member member = memberService.findById(2L);
        System.out.println(member);
    }

    @Test
    void cartService(){
        Member member = memberService.findById(2L);
    }

    @Test
    @Transactional
    void searchTest(){
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(1L);
        ids.add(3L);
        Category category = categoryService.findById(1L);
        List<Category> subCategories = category.getSubcategories();
        subCategories.forEach(c->System.out.println(c.getId()));

        subCategories = categoryService.getSubCategoriesFromIds(ids);
        subCategories.forEach(c->System.out.println(c.getId()));
        System.out.println("getSubCategoryIdsFromIds(): "+categoryService.getSubCategoryIdsFromIds(ids));
    }

}
