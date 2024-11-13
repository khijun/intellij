package edu.du.sb1031.dto;

import edu.du.sb1031.entity.Cart;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class CartList {
    List<Cart> cartList;
}
