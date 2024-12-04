package edu.du.sb1202_test_lms.repository;

import edu.du.sb1202_test_lms.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignment_Id(Long assignmentId);

    @Query("SELECT s FROM Submission s WHERE s.user.email = :email AND s.assignment.id = :assignmentId")
    Optional<Submission> findByUserAndAssignment(@Param("email") String email, @Param("assignmentId") Long assignmentId);
}
