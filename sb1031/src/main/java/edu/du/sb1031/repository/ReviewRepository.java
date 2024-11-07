package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findByItem(Item item);
}
