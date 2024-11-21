package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // 이름으로 아이템 검색
    public List<Item> findByNameLike(String name);

    // 카테고리별 총 평점 내림차순 정렬
    @Query("SELECT i FROM Item i " +
            "JOIN i.reviews r " +
            "WHERE i.category.id IN :categoryIds " +
            "GROUP BY i.id " +
            "ORDER BY SUM(r.rating) DESC")
    public List<Item> findByOrderByTotalRating(@Param("categoryIds") List<Long> categoryIds);

    // 카테고리별 판매량 내림차순 정렬
    @Query("SELECT i FROM Order o " +
            "JOIN o.orderItems oi " +
            "RIGHT OUTER JOIN oi.item i " +
            "WHERE i.category.id IN :categoryIds " +
            "GROUP BY i.id " +
            "ORDER BY SUM(oi.quantity) DESC")
    public List<Item> findByOrderBySellDesc(@Param("categoryIds") List<Long> categoryIds);

    // 카테고리별 리뷰 수 내림차순 정렬
    @Query("SELECT i FROM Item i " +
            "LEFT JOIN i.reviews r " +
            "WHERE i.category.id IN :categoryIds " +
            "GROUP BY i.id " +
            "ORDER BY COUNT(r) DESC")
    public List<Item> findByOrderByTotalReview(@Param("categoryIds") List<Long> categoryIds);

    // 카테고리별 가격 내림차순 정렬
    @Query("SELECT i FROM Item i " +
            "WHERE i.category.id IN :categoryIds " +
            "ORDER BY i.price DESC")
    public List<Item> findByOrderByPriceDesc(@Param("categoryIds") List<Long> categoryIds);

    // 카테고리별 가격 오름차순 정렬
    @Query("SELECT i FROM Item i " +
            "WHERE i.category.id IN :categoryIds " +
            "ORDER BY i.price ASC")
    public List<Item> findByOrderByPriceAsc(@Param("categoryIds") List<Long> categoryIds);

    // 카테고리별 생성일 내림차순 정렬
    @Query("SELECT i FROM Item i " +
            "WHERE i.category.id IN :categoryIds " +
            "ORDER BY i.createDateTime DESC")
    public List<Item> findByOrderByCreateDateTimeDesc(@Param("categoryIds") List<Long> categoryIds);

    public List<Item> findByCategoryId(Long categoryId);
    @Query("SELECT i.id, i.name FROM Item i " +
            "WHERE i.category.id = :categoryId")
    public List<Object[]> findByCategoryIdByStockDto(@Param("categoryId") Long categoryId);
}
