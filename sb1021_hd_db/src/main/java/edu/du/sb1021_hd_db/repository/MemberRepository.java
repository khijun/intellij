package edu.du.sb1021_hd_db.repository;

import edu.du.sb1021_hd_db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
