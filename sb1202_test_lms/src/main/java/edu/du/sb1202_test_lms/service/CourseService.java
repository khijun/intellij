package edu.du.sb1202_test_lms.service;

import edu.du.sb1202_test_lms.dto.CreateCourseDto;
import edu.du.sb1202_test_lms.entity.Course;
import edu.du.sb1202_test_lms.entity.Enrollment;
import edu.du.sb1202_test_lms.repository.CourseRepository;
import edu.du.sb1202_test_lms.repository.EnrollmentRepository;
import edu.du.sb1202_test_lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    public void createCourse(CreateCourseDto courseDto) {
        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setInstructor(userRepository.findById(courseDto.getInstructorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid instructor ID")));
        courseRepository.save(course);
    }

    public void enrollCourse(Long courseId, String username) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(courseRepository.findById(courseId).orElseThrow());
        enrollment.setUser(userRepository.findByEmail(username).orElseThrow());
        enrollment.setStatus("ENROLLED");
        enrollmentRepository.save(enrollment);
    }

    public List<Course> getCoursesByUser(String username) {
        return enrollmentRepository.findCoursesByUserEmail(username);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}

