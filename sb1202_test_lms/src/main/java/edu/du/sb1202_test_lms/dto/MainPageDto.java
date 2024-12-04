package edu.du.sb1202_test_lms.dto;

import lombok.*;

import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainPageDto {
    private List<CourseDto> recentCourses;
    private List<AssignmentDto> upcomingAssignments;
    private List<StudentDto> recentStudents;
    private List<String> announcements;
}
