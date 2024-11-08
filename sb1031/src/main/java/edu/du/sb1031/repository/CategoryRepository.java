package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findByParent(Category parent);
}
