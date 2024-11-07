package edu.du.sb1031.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "delivery")
    @ToString.Exclude
    private Order order;
    private String zipcode; // 우편번호
    private String baseAddress; // 기본 주소
    private String detailAddress; // 상세 주소
    private String recipientName; // 수령인 이름
    private String phone;
}