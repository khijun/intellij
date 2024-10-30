package com.example.demo.member.entity;

import com.example.demo.fileuploadboard.entity.Board;
import com.example.demo.survey.AnsweredData;
import lombok.*;
import org.apache.ibatis.annotations.One;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "ans_id")
    private AnsweredData answeredData;
}
