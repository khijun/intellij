package edu.du.sb1023_3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "response")
public class Response {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;
    @ManyToOne
    @JoinColumn(name = "answerId")
    @ToString.Exclude
    private AnsweredData answerId;

    public Response(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
