package edu.du.sb1031.repository;

import edu.du.sb1031.dto.MainPageData;
import edu.du.sb1031.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findByParent(Category parent);
    @Query("SELECT c FROM Category c WHERE c.parent = :id")
    public Optional<List<Category>> hasChildren(@Param("id") Long id);
    public List<Category> findByOrderByIdAsc();
    public List<Category> findByOrderByIdDesc();

    @Query("SELECT new edu.du.sb1031.dto.MainPageData(c.id, i, SUM(oi.quantity)) FROM Category c " +
            "LEFT JOIN c.items i " +
            "LEFT JOIN i.orderItems oi " +
            "GROUP BY c.id, i ")
    public List<MainPageData> findForMainPage();

}
