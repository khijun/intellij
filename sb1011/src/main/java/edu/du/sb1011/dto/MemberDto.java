package edu.du.sb1011.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MemberDto {
    private int memberIdx;
    private String name;
    private String id;
    private String password;
    private String joinDatetime;
}
