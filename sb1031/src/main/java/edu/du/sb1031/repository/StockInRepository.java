package edu.du.sb1031.repository;

import edu.du.sb1031.entity.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockInRepository extends JpaRepository<StockIn,Long> {
}
