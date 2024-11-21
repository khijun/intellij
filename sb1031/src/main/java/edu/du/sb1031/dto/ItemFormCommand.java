package edu.du.sb1031.dto;

import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Data
public class ItemFormCommand {
    Item item;
    List<Category> categories;
    MultipartFile imageFile;
}
