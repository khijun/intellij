package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Member;
import edu.du.sb1031.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByMember(Member member);
}
