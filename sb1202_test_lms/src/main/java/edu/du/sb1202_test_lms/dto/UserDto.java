package edu.du.sb1202_test_lms.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class UserDto {
    private String name;
    private String email;
    private String password;
}
