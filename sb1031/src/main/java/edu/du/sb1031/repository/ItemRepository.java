package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByNameLike(String name);
    @Query("SELECT i FROM Item i " +
            "JOIN i.reviews r " +
            "GROUP BY i.id " +
            "ORDER BY SUM(r.rating) DESC")
    public List<Item> findByOrderByTotalRating();
    @Query("SELECT i FROM Order o " +
            " JOIN o.orderItems oi " +
            " right outer join oi.item i " +
            "GROUP BY i.id " +
            "ORDER BY SUM(oi.quantity) desc")
    public List<Item> findByOrderBySellDesc();
    @Query("SELECT i FROM Item i " +
            " LEFT JOIN i.reviews r " +
            "GROUP BY i.id " +
            "ORDER BY count(r) desc")
    public List<Item> findByOrderByTotalReview();
    public List<Item> findByOrderByPriceDesc(); // 높은순 내림차순
    public List<Item> findByOrderByPriceAsc(); // 낮은순 오름차순
    public List<Item> findByOrderByCreateDateTimeDesc(); // 최근순
}
