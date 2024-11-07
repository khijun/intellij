package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.entity.Item;
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

    @RequestMapping("/")
    public String init(HttpServletRequest request, Model model) {
        AuthInfo authInfo = new AuthInfo(2L);
        request.getSession().setAttribute("authInfo", authInfo);
        System.out.println("멤버 번호 저장"); // 임시코드
        List<Item> items =  itemService.findAll();
        model.addAttribute("items", items);
        return "/main/main";
    }

    @GetMapping("/search/{str}")
    public String search(@PathVariable String str, HttpServletRequest request, Model model) {
        str = '%'+str+'%';
        List<Item> items = itemService.findByNameLike(str);
        model.addAttribute("items", items);
        return "/main/main";
    }

    //test code
//    @RequestMapping("/navbar")
//    public String navbar() {
//        return "/fragments/navbar";
//    }
}

