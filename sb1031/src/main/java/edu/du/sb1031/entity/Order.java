package edu.du.sb1031.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne
    @JoinColumn(name = "delivery_id")
    @ToString.Exclude
    private Delivery delivery;
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    @ToString.Exclude
    private List<OrderItem> orderItems = new ArrayList<>();
    private String status; // 주문 상태 (예: "주문 접수", "배송 중", "배송 완료")

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }
    public void subOrderItem(OrderItem orderItem) {
        orderItem.setOrder(null);
        orderItems.remove(orderItem);
    }
}
