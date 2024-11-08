package edu.du.sb1031.controller;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Review;
import edu.du.sb1031.service.ItemService;
import edu.du.sb1031.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemPageController {

    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping("/item/{id}")
    public String itemDetail(@PathVariable Long id, Model model){
        Item item = itemService.findById(id);
        List<Review> reviews = reviewService.findByItem(item);
        model.addAttribute("item", item);
        model.addAttribute("reviews", reviews);
        Double ratingAvg = null;
        if(!reviews.isEmpty()){
            ratingAvg = 0.0;
            for(Review review : reviews){
                ratingAvg += review.getRating();
            }
            ratingAvg /= reviews.size();
        }
        model.addAttribute("rating_avg", ratingAvg);
        return "/item/item_page";
    }
}
