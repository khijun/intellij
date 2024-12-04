package edu.du.sb1202_test_lms.repository;

import edu.du.sb1202_test_lms.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCourse_Id(Long courseId);
    List<Assignment> findTop5ByDueDateAfter(LocalDateTime dueDate);

}
