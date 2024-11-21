package edu.du.sb1031.repository;

import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    public List<Cart> findByMember(Member member);
    public Cart findByMemberAndItem(Member member, Item item);

    @Query("SELECT c.quantity AS quantity, " +
            "c.item.price AS item_price, " +
            "c.item.name AS item_name, " +
            "c.item.imageName AS item_imageName, " +
            "c.item.id AS item_id " +
            "FROM Cart c " +
            "WHERE c.member = :member")
    public List<Map<String, Object>> findByMemberByJson(Member member);



}
