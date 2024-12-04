package edu.du.sb1202_test_lms.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@ToString
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime dueDate;
    private String description;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Submission> submissions;

}
