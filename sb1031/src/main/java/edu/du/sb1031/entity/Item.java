package edu.du.sb1031.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private int quantity;
    private String company;
    private String imageName;

    public String getImageName() {
        if (this.imageName == null) {
            return "item_page_test_img.png";
        }
        return imageName;
    }

    //      상품 이미지 하나
//    private int category; // 카테고리 번호
}
