package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.service.CategoryService;
import edu.du.sb1031.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BeginController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/test")
    public String init(HttpServletRequest request, Model model) {
        AuthInfo authInfo = new AuthInfo(2L);
        request.getSession().setAttribute("authInfo", authInfo);
        System.out.println("멤버 번호 저장"); // 임시코드
        List<Item> items =  itemService.findAll();
        model.addAttribute("items", items);
        return "/main/test";
    }

    @GetMapping("/search/{str}")
    public String search(@PathVariable String str, HttpServletRequest request, Model model) {
        str = '%'+str+'%';
        List<Item> items = itemService.findByNameLike(str);
        model.addAttribute("items", items);
        return "/main/main_old";
    }

    @GetMapping("/")
    public String test(HttpServletRequest request, Model model) {
        AuthInfo authInfo = new AuthInfo(2L);
        request.getSession().setAttribute("authInfo", authInfo);
        System.out.println("멤버 번호 저장"); // 임시코드

        List<Category> categories = categoryService.findAll();
        categories.removeIf(c -> c.getParent() == null);//향상된 포문이엇는데 인텔리제이가 바꿈;
        model.addAttribute("categories", categories);
        return "/main/main";
    }

    //test code
//    @RequestMapping("/navbar")
//    public String navbar() {
//        return "/fragments/navbar";
//    }
}

