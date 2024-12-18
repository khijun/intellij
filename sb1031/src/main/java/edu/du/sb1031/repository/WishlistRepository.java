package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    public List<Wishlist> findByMemberIdOrderByIdDesc(Long memberId);
}
