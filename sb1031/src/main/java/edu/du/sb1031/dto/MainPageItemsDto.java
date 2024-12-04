package edu.du.sb1031.dto;

import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MainPageItemsDto {
    private Long categoryId;
    private Item bannerItem;
    private List<Item> items;
}
