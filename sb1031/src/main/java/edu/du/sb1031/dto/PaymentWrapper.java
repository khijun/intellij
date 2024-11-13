package edu.du.sb1031.dto;

import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.entity.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentWrapper {
    Delivery delivery;
    List<Cart> carts = new ArrayList<>();
}
