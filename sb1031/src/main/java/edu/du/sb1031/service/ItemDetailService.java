package edu.du.sb1031.service;

import edu.du.sb1031.dto.ItemDetailDto;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemDetailService {
    private final AuthService authService;
    private final ItemService itemService;
    private final ReviewService reviewService;

    public ItemDetailDto createItemDetailDto(Long itemId){
        Item item = itemService.findById(itemId);
        List<Review> reviews = reviewService.findByItem(item);
        Double ratingAverage = 0.0;
        if(reviews.isEmpty()) {
            ratingAverage = null;
        }else{
            for (Review review : reviews) {
                ratingAverage += review.getRating();
            }
        }
        return ItemDetailDto.builder()
                .item(item)
                .reviews(reviews)
                .ratingAverage(ratingAverage)
                .build();
    }
}
