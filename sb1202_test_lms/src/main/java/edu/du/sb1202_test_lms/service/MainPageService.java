package edu.du.sb1202_test_lms.service;

import edu.du.sb1202_test_lms.dto.AssignmentDto;
import edu.du.sb1202_test_lms.dto.CourseDto;
import edu.du.sb1202_test_lms.dto.MainPageDto;
import edu.du.sb1202_test_lms.dto.StudentDto;
import edu.du.sb1202_test_lms.repository.AssignmentRepository;
import edu.du.sb1202_test_lms.repository.CourseRepository;
import edu.du.sb1202_test_lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final CourseRepository courseRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    public MainPageDto getMainPageData() {
        // 강의 데이터 조회
        List<CourseDto> recentCourses = courseRepository.findTop5ByOrderByCreateDateTimeDesc()
                .stream()
                .map(course -> CourseDto.builder()
                        .title(course.getTitle())
                        .description(course.getDescription())
                        .build())
                .collect(Collectors.toList());

        // 과제 데이터 조회
        List<AssignmentDto> upcomingAssignments = assignmentRepository.findTop5ByDueDateAfter(LocalDateTime.now())
                .stream()
                .map(assignment -> AssignmentDto.builder()
                        .title(assignment.getTitle())
                        .description(assignment.getDescription())
                        .build())
                .collect(Collectors.toList());

        // 학생 데이터 조회
        List<StudentDto> recentStudents = userRepository.findTop5ByOrderByRegistrationDateDesc()
                .stream()
                .map(student -> StudentDto.builder()
                        .name(student.getName())
                        .email(student.getEmail())
                        .build())
                .collect(Collectors.toList());

        // MainPageDto 객체에 데이터 담기 (Builder 패턴 사용)
        return MainPageDto.builder()
                .recentCourses(recentCourses)
                .upcomingAssignments(upcomingAssignments)
                .recentStudents(recentStudents)
                .build();
    }
}
