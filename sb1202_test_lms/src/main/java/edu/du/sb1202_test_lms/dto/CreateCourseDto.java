package edu.du.sb1202_test_lms.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCourseDto {
    private String title;
    private String description;
    private Long instructorId;
}

