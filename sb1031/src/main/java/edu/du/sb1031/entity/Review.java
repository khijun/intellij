package edu.du.sb1031.entity;

import edu.du.sb1031.config.ReviewStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private String content;
    private LocalDateTime replyTime;
    private int rating;
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;
    private int likes;

}
