package edu.du.sb1031.dto;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long itemId;
    private String content;
    private int rating;
}
