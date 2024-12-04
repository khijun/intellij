package edu.du.sb1031.controller;

import edu.du.sb1031.dto.Define;
import edu.du.sb1031.dto.PaymentWrapper;
import edu.du.sb1031.entity.*;
import edu.du.sb1031.exception.ItemNotFoundException;
import edu.du.sb1031.repository.CartRepository;
import edu.du.sb1031.repository.MemberRepository;
import edu.du.sb1031.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final AuthService authService;

    @RequestMapping
    public String cart(Model model) {
        Member member = authService.getCurrentMember();
        List<Cart> carts = cartService.findByMember(member);
        model.addAttribute("paymentWrapper", new PaymentWrapper(null, carts));
        return "/cart/cart";
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        Member member = authService.getCurrentMember();
        List<Cart> carts = cartService.findByMember(member);
        PaymentWrapper pw = new PaymentWrapper();
        pw.setCarts(carts);
        model.addAttribute("paymentWrapper", pw);

        int orderPrice = 0;
        for (Cart cart : carts) {
            orderPrice += cart.getQuantity() * cart.getItem().getPrice();
            model.addAttribute("orderPrice", orderPrice);
            model.addAttribute("deliveryCost", Define.DELIVERY); // 임의의 값
        }
        return "/cart/payment";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable(name = "id") Long itemId) {
        Cart cart = new Cart();
        Member member = authService.getCurrentMember();
        Item item = itemService.findById(itemId);
        cart.setMember(member);
        cart.setItem(item);
        cart.setQuantity(1);    // 기본값, 수정 여부 불확실
        cartService.add(cart);
        return "redirect:/cart";
    }

    @PatchMapping("/updateNumber/{cartId}")
    public ResponseEntity<String> updateNumber(@RequestBody Map<String, Object> payload, @PathVariable Long cartId) {
        int newValue = Integer.parseInt(payload.get("number").toString());

        Cart cart = cartService.findById(cartId);

        cart.setQuantity(newValue);
        cartService.save(cart);
        return ResponseEntity.ok("DB 업데이트 완료");
    }

    @DeleteMapping("/deleteItem/{cartId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.findById(cartId);
            cartService.delete(cart);
            return ResponseEntity.ok("Item deleted successfully");
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete item");
        }
    }

}
