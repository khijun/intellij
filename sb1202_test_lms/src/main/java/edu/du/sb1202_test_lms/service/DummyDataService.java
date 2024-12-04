package edu.du.sb1202_test_lms.service;

import edu.du.sb1202_test_lms.entity.*;
import edu.du.sb1202_test_lms.repository.AssignmentRepository;
import edu.du.sb1202_test_lms.repository.CourseRepository;
import edu.du.sb1202_test_lms.repository.EnrollmentRepository;
import edu.du.sb1202_test_lms.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DummyDataService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final AssignmentRepository assignmentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public DummyDataService(UserRepository userRepository,
                            CourseRepository courseRepository,
                            AssignmentRepository assignmentRepository,
                            EnrollmentRepository enrollmentRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.assignmentRepository = assignmentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public void generateDummyData() {
        // 1. 사용자 생성
        User instructor = new User();
        instructor.setName("John Doe");
        instructor.setEmail("instructor@example.com");
        instructor.setPassword("password");
        instructor.setRole(Role.INSTRUCTOR);
        userRepository.save(instructor);

        User student = new User();
        student.setName("Jane Smith");
        student.setEmail("student@example.com");
        student.setPassword("password");
        student.setRole(Role.STUDENT);
        userRepository.save(student);

        // 2. 강의 생성
        Course course = new Course();
        course.setTitle("Introduction to Java");
        course.setDescription("Learn the basics of Java programming.");
        course.setInstructor(instructor);
        courseRepository.save(course);

        // 3. 수강 등록
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(student);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);

        // 4. 과제 생성
        Assignment assignment = new Assignment();
        assignment.setTitle("Java Basics Homework");
        assignment.setDescription("Complete the exercises on variables and loops.");
        assignment.setCourse(course);
        assignmentRepository.save(assignment);
    }
}

