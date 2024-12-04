package edu.du.sb1031.dto;

import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MainPageData {
    private Long categoryId;
    private Item item;
    private Long sellCount;

    public Long getSellCount(){
        return sellCount==null?0:sellCount;
    }
}
