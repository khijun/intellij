package edu.du.sb1202_test_lms.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StudentDto {
    private String name;
    private String email;
}
