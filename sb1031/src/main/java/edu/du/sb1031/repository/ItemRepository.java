package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByNameLike(String name);
    public List<Item> findByOrderByPrice();
    public List<Item> findByOrderByPriceDesc();
    @Query("SELECT i FROM Item i " +
            "JOIN i.reviews r " +
            "GROUP BY i.id " +
            "ORDER BY SUM(r.rating) DESC")
    List<Item> findItemsOrderedByTotalRating();
}
