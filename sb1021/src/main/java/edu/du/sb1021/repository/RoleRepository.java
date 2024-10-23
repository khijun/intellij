package edu.du.sb1021.repository;

import edu.du.sb1021.entity.Member;
import edu.du.sb1021.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
