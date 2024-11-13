package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.dto.PaymentWrapper;
import edu.du.sb1031.entity.*;
import edu.du.sb1031.exception.NotEnoughStockException;
import edu.du.sb1031.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final MemberService ms;
    private final OrderService os;
    private final DeliveryService ds;
    private final OrderItemService ois;
    private final ItemService is;

    @PostMapping("/payment")
    public String buy(HttpServletRequest request, Model model, Delivery delivery, @ModelAttribute PaymentWrapper pw) {
        try {
            Long memberId = ((AuthInfo) request.getSession().getAttribute("authInfo")).getId();
            Member member = ms.findById(memberId);

            ds.save(delivery);

            Order order = new Order(null, member, delivery, null, "배송중");
            os.save(order);

            List<Cart> carts = pw.getCarts();
            List<OrderItem> orderItems = new ArrayList<>();
            for (Cart cart : carts) {
                OrderItem orderItem = new OrderItem(null, order, cart.getQuantity(), cart.getItem());
                orderItems.add(orderItem);
                ois.save(orderItem);
            }
            order.setOrderItems(orderItems);

            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.getItem().setStock(orderItem.getItem().getStock() - orderItem.getQuantity());
                if (orderItem.getItem().getStock() < 0) {
                    throw new NotEnoughStockException();
                }
                is.save(orderItem.getItem());
            }
        } catch (NotEnoughStockException e) {
            System.out.println("PaymentController.buy: 아이템 재고 부족");
        }catch(Exception e){
            System.out.println("PaymentController.buy: 인증 실패");
        }
        return "redirect:/";
    }
}
