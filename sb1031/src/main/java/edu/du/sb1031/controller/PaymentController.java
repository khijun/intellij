package edu.du.sb1031.controller;

import edu.du.sb1031.dto.PaymentWrapper;
import edu.du.sb1031.entity.*;
import edu.du.sb1031.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final MemberService ms;
    private final OrderService os;
    private final DeliveryService ds;
    private final OrderItemService ois;
    private final ItemService is;
    private final AuthService authService;

    @PostMapping("/payment")
    public String buy(@ModelAttribute PaymentWrapper pw) {
        Member member = authService.getCurrentMember();

        ds.save(pw.getDelivery());
        Order order = os.save(member, pw.getDelivery());

        List<Cart> carts = pw.getCarts();
        order.setOrderItems(ois.saveFromCarts(carts, order));

        return "redirect:/";
    }
}
