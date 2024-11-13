package edu.du.sb1031.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int stock;
    private String company;
    private String imageName;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;
    @OneToMany(mappedBy = "item")
    @ToString.Exclude
    private List<Review> reviews;
    private String content;
    private LocalDateTime createDateTime;


    public String getImageName() {
        if (this.imageName == null) {
            return "item_page_test_img.png";
        }
        return imageName;
    }
}
