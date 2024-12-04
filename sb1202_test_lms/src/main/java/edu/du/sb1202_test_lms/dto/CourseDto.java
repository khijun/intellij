package edu.du.sb1202_test_lms.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private String instructorName;

}

