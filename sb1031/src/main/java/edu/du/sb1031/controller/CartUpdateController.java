package edu.du.sb1031.controller;

import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.exception.ItemNotFoundException;
import edu.du.sb1031.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CartUpdateController {

    @Autowired
    private CartService cs;

    @PostMapping("/updateNumber")
    public ResponseEntity<String> updateNumber(@RequestBody Map<String, Object> payload) {
        System.out.println("!!! updateNumber");
        System.out.println(payload);
        Long cartId = Long.valueOf(payload.get("cartId").toString());
        int newValue = Integer.parseInt(payload.get("number").toString());

        Cart cart = cs.findById(cartId);

        cart.setQuantity(newValue);
        cs.save(cart);
        System.out.println("!!! updateNumber() 완료");
        return ResponseEntity.ok("DB 업데이트 완료");
    }

    @PostMapping("/deleteItem")
    public ResponseEntity<String> deleteItem(@RequestBody Map<String, Object> payload) {
        try {
            Long cartId = ((Number) payload.get("cartId")).longValue();
            System.out.println(cartId);
            Cart cart = cs.findById(cartId);
            System.out.println(cart);
            cs.delete(cart);
            System.out.println("!!! deleteItem");
            return ResponseEntity.ok("Item deleted successfully");
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(500).body("Failed to delete item");
        }
    }
}
