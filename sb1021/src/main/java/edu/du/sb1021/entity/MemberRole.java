package edu.du.sb1021.entity;

import javax.persistence.*;

@Entity
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @JoinTable(name = "role",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private String role;
}
