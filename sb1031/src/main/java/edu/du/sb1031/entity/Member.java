package edu.du.sb1031.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
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
    @Builder.Default
    private LocalDateTime birthday = LocalDateTime.now();
    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<Cart> carts = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<StockIn> stockIns = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<Wishlist> wishlists = new ArrayList<>();
}
