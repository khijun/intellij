package edu.du.sb1021.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
