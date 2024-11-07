package edu.du.sb1031.controller;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.service.ItemService;
import edu.du.sb1031.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ItemPageController {

    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping("/item/{id}")
    public String itemDetail(@PathVariable Long id, Model model){
        Item item = itemService.findById(id);
        System.out.println(reviewService.findByItem(item));
        model.addAttribute("item", item);
        model.addAttribute("reviews", reviewService.findByItem(item));
        return "/item/item_page";
    }
}
