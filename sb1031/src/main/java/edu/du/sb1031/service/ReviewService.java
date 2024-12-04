package edu.du.sb1031.service;

import edu.du.sb1031.config.ReviewStatus;
import edu.du.sb1031.dto.ReviewDto;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Review;
import edu.du.sb1031.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final AuthService authService;
    private final ItemService itemService;


    public List<Review> findByItem(Item item) {
        return reviewRepository.findByItem(item);
    }

    public ReviewDto save(ReviewDto reviewDto) { // 예외처리 필요할수도, 고려 후 수정
        System.out.println("아이템: "+itemService.findById(reviewDto.getItemId()));
        Review review = Review.builder()
                .item(itemService.findById(reviewDto.getItemId()))
                .member(authService.getCurrentMember())
                .content(reviewDto.getContent())
                .replyTime(LocalDateTime.now())
                .rating(reviewDto.getRating())
                .status(ReviewStatus.NORMAL)
                .likes(0)
                .build();
        reviewRepository.save(review);
        return reviewDto;
    }

}
