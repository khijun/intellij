package edu.du.sb1031.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;
    private int quantity;
    @Builder.Default
    private LocalDateTime stockInDate = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
}
