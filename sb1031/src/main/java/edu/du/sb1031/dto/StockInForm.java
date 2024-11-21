package edu.du.sb1031.dto;

import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StockInForm {
    private StockDto stockDto;
    private List<Category> categories;
    private List<Item> items;
}
