package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.dto.CartList;
import edu.du.sb1031.dto.Define;
import edu.du.sb1031.dto.PaymentWrapper;
import edu.du.sb1031.entity.*;
import edu.du.sb1031.exception.ItemNotFoundException;
import edu.du.sb1031.exception.NotEnoughStockException;
import edu.du.sb1031.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    private final CartService cartService;

    @GetMapping
    public String cart(HttpServletRequest request, Model model) {
        try {
            Long memberId = ((AuthInfo) request.getSession().getAttribute("authInfo")).getId();
            System.out.println(memberId);
            Member member = ms.findById(memberId);
            System.out.println(member);
            List<Cart> carts = cs.findByMember(member);
            System.out.println(carts);
            model.addAttribute("paymentWrapper", new PaymentWrapper(null,carts));
        } catch (Exception e) {
            System.out.println("CartController.cart: 인증 실패");
        }
        return "/cart/cart";
    }

    @GetMapping("/payment")
    public String payment(@SessionAttribute AuthInfo authInfo,HttpServletRequest request, Model model) {
        try {
            Long memberId = authInfo.getId();
            Member member = ms.findById(memberId);
            List<Cart> carts = cs.findByMember(member);
            PaymentWrapper pw = new PaymentWrapper();
            pw.setCarts(carts);
            model.addAttribute("paymentWrapper", pw);

            int orderPrice = 0;
            for (Cart cart : carts) {
                orderPrice += cart.getQuantity() * cart.getItem().getPrice();
            }
            model.addAttribute("orderPrice", orderPrice);
            model.addAttribute("deliveryCost", Define.DELIVERY); // 임의의 값
        } catch (Exception e) {
            System.out.println("CartController.get.payment: 인증 실패");
        }
        return "/cart/payment";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable(name = "id") Long itemId, Model model, HttpServletRequest request) {
        Cart cart = new Cart();
        Member member;
        Item item;
        try {
            Long memberId = ((AuthInfo) request.getSession(true).getAttribute("authInfo")).getId();
            member = ms.findById(memberId);
            System.out.println("멤버: " + member);
            item = is.findById(itemId);
            System.out.println("아이템: " + item);
            if (member == null || item == null) {
                throw new Exception();
            }
            System.out.println("추가중");
            cart.setMember(member);
            cart.setItem(item);
            cart.setQuantity(1);    // 기본값, 수정 여부 불확실
            cs.add(cart);
        } catch (Exception e) {
            System.out.println("CartController.add: 인증 실패");
        }
        return "redirect:/cart";
    }

    @GetMapping("/sub/{id}")
    public String sub(@PathVariable(name = "id") Long itemId, Model model, HttpServletRequest request) {
        try {
            Long memberId = ((AuthInfo) request.getSession(true).getAttribute("authInfo")).getId();
            Member member = ms.findById(memberId);
            Item item = is.findById(itemId);
            if (member == null || item == null) {
                throw new Exception();
            }
            Cart cart = cs.findByMemberAndItem(member, item);
            cartService.delete(cart);
        } catch (Exception e) {
            System.out.println("CartController.sub: 인증 실패");
        }
        return "redirect:/cart";
    }

}
