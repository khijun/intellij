package edu.du.sb1031.controller;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item/")
public class ItemController {
    private final ItemService is;

    @GetMapping("itemForm")
    public String itemForm(Model model){
        model.addAttribute("item", new Item());
        return "/item/itemForm";
    }

    @PostMapping
    public void save(Item item){
        is.save(item);
    }


}
