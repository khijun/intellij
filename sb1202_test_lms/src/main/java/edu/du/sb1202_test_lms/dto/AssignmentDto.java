package edu.du.sb1202_test_lms.dto;

import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDto {
    private Long id;
    private String title;
    private String description;
    private Long courseId;
}
