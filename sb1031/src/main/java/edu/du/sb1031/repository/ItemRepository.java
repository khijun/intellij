package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByNameLike(String name);
}
