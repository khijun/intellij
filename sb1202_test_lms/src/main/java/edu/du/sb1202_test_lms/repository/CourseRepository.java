package edu.du.sb1202_test_lms.repository;

import edu.du.sb1202_test_lms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructor_Id(Long instructorId); // 특정 강사가 만든 강의 조회
    List<Course> findTop5ByOrderByCreateDateTimeDesc();
}

