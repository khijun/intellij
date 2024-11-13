package edu.du.sb1031.controller;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final ItemService itemService;

    @GetMapping("/search/{str}")
    public String search(@PathVariable String str, HttpServletRequest request, Model model) {
        str = '%'+str+'%';
        List<Item> items = itemService.findByNameLike(str);
        model.addAttribute("items", items);
        return "/search/search";
    }

    @GetMapping("/search")
    public String recommend(Model model, @RequestParam(name = "type") String searchType) {
        List<Item> items = itemService.findByType(searchType);
        model.addAttribute("items", items);
        return "/search/search";
    }
}
