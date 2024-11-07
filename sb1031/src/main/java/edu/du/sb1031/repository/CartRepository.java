package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    public List<Cart> findByMember(Member member);
    public Cart findByMemberAndItem(Member member, Item item);
}
