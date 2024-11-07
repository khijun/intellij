package edu.du.sb1031.service;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Review;
import edu.du.sb1031.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository rr;

    public List<Review> findAll() {
        return rr.findAll();
    }

    public List<Review> findByItem(Item item) {
        return rr.findByItem(item);
    }

}
