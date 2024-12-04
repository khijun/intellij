package edu.du.sb1202_test_lms.repository;

import edu.du.sb1202_test_lms.entity.Course;
import edu.du.sb1202_test_lms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("SELECT e.course FROM Enrollment e WHERE e.user.email = :email")
    List<Course> findCoursesByUserEmail(@Param("email") String email);

    List<Enrollment> findByCourse_Id(Long courseId);
}

