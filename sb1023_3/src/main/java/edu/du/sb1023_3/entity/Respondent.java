package edu.du.sb1023_3.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "respondent")
public class Respondent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    private String location;
//    @JoinColumn(name = "answerId")
//    @OneToOne
//    @ToString.Exclude
//    private AnsweredData answerId;

    public Respondent(int age, String loction) {
        this.age = age;
        this.location = loction;
    }
}
