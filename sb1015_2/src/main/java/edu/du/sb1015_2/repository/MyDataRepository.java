package edu.du.sb1015_2.repository;

import edu.du.sb1015_2.entity.MyData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyDataRepository extends JpaRepository<MyData, Long> {
    Optional<MyData> findByMail(String mail);
}
