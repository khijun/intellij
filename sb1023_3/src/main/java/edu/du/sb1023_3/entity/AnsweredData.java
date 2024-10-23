package edu.du.sb1023_3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answered_data")
public class AnsweredData {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "answerId")
    private List<Response> responses = new ArrayList<>();
    @OneToOne
    private Respondent res;

    public void addResponse(Response response) {
        this.responses.add(response);
    }
}
