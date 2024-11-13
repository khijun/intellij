package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.service.CategoryService;
import edu.du.sb1031.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes(names = "authInfo")
public class BeginController {

    @ModelAttribute("authInfo")
    public AuthInfo authInfo() {
        System.out.println("멤버 번호 저장"); // 임시코드
        return new AuthInfo(2L);
    }

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

    @GetMapping("/")
    public String test(HttpServletRequest request, Model model) {
        List<Category> categories = categoryService.findAll();
        categories.removeIf(c -> c.getParent() == null);//향상된 포문이엇는데 인텔리제이가 바꿈;
        model.addAttribute("categories", categories);
        return "/main/main";
    }
}

