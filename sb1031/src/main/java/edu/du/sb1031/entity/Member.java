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
    private String role;// user, delete, freeze, admin
    private char gender;
    @Builder.Default
    private LocalDateTime birthday = LocalDateTime.now();
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    List<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    List<Cart> carts = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    List<StockIn> stockIns = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    List<Wishlist> wishlists = new ArrayList<>();
}
