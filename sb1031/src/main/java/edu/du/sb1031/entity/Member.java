package edu.du.sb1031.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    private char role;// user, delete, freeze, admin
    private char gender;
    private LocalDateTime birthday;
    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<Cart> carts = new ArrayList<>();
}
