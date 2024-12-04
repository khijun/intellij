package edu.du.sb1031.dto;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Review;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDetailDto {
    private Item item;
    private List<Review> reviews;
    private Double ratingAverage = 0.0;
}
