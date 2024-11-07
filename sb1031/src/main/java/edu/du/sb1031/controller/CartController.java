package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.entity.*;
import edu.du.sb1031.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cs;
    private final MemberService ms;
    private final ItemService is;
    private final OrderItemService ois;
    private final OrderService os;
    private final DeliveryService ds;

    @GetMapping
    public String cart(HttpServletRequest request, Model model) {
        try{
            Long memberId = ((AuthInfo) request.getSession().getAttribute("authInfo")).getId();
            System.out.println(memberId);
            Member member = ms.findById(memberId);
            System.out.println(member);
            List<Cart> carts = cs.findByMember(member);
            System.out.println(carts);
            model.addAttribute("carts", carts);
        }catch(Exception e){
            System.out.println("CartController.cart: 인증 실패");
        }
        return "/cart/cart";
    }
    @GetMapping("/payment")
    public String payment(HttpServletRequest request, Model model) {
        try{
            Long memberId = ((AuthInfo) request.getSession().getAttribute("authInfo")).getId();
            System.out.println(memberId);
            Member member = ms.findById(memberId);
            System.out.println(member);
            List<Cart> carts = cs.findByMember(member);
            System.out.println(carts);
            model.addAttribute("carts", carts);

            int orderTotal = 0;
            for (Cart cart : carts) {
                orderTotal += cart.getQuantity() * cart.getItem().getPrice();
            }

            model.addAttribute("orderTotal", orderTotal);
            model.addAttribute("deliveryCost", 5000); // 임의의 값
            model.addAttribute("delivery", new Delivery());
        }catch(Exception e){
            System.out.println("CartController.payment: 인증 실패");
        }
        return "/cart/payment";
    }

    @PostMapping("/payment")
    public String buy(HttpServletRequest request, Model model, Delivery delivery) {
        try{
            Long memberId = ((AuthInfo) request.getSession().getAttribute("authInfo")).getId();
            Member member = ms.findById(memberId);

            ds.save(delivery);

            Order order = new Order(null, member, delivery, null, "배송중");
            os.save(order);

            List<Cart> carts = cs.findByMember(member);
            for(Cart cart : carts){
                OrderItem orderItem = new OrderItem(null, order, cart.getQuantity(), cart.getItem());
                ois.save(orderItem);
            }

        }catch(Exception e){
            System.out.println("CartController.buy: 인증 실패");
        }
        return "redirect:/";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable(name = "id") Long itemId, Model model, HttpServletRequest request) {
        Cart cart = new Cart();
        Member member;
        Item item;
        try{
            Long memberId = ((AuthInfo)request.getSession(true).getAttribute("authInfo")).getId();
            member = ms.findById(memberId);
            System.out.println("멤버: " + member);
            item = is.findById(itemId);
            System.out.println("아이템: " + item);
            if(member == null||item == null) {
                throw new Exception();
            }
            System.out.println("추가중");
            cart.setMember(member);
            cart.setItem(item);
            cart.setQuantity(1);    // 기본값, 수정 여부 불확실
            cs.add(cart);
            model.addAttribute("member", member);
        }catch (Exception e) {
            System.out.println("CartController.add: 인증 실패");
        }
        return "redirect:/cart";
    }
}
