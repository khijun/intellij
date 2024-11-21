package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.dto.Define;
import edu.du.sb1031.dto.PaymentWrapper;
import edu.du.sb1031.entity.*;
import edu.du.sb1031.exception.ItemNotFoundException;
import edu.du.sb1031.exception.MemberNotFoundException;
import edu.du.sb1031.repository.CartRepository;
import edu.du.sb1031.repository.MemberRepository;
import edu.du.sb1031.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping
    public String cart(HttpServletRequest request, @SessionAttribute AuthInfo authInfo, Model model) {
        try {
            Member member = memberService.findById(authInfo.getId());
            List<Cart> carts = cartService.findByMember(member);
            model.addAttribute("paymentWrapper", new PaymentWrapper(null, carts));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CartController.cart: 인증 실패");
        }
        return "/cart/cart";
    }

    @GetMapping("/payment")
    public String payment(@SessionAttribute AuthInfo authInfo, HttpServletRequest request, Model model) {
        try {
            Member member = memberService.findById(authInfo.getId());
            List<Cart> carts = cartService.findByMember(member);
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
    public String add(@PathVariable(name = "id") Long itemId, @SessionAttribute AuthInfo authInfo, RedirectAttributes redirectAttributes) {
        System.out.println("authInfo 아이디: "+authInfo.getId());
        Cart cart = new Cart();
        Member member;
        Item item;
        try {
            member = memberService.findById(authInfo.getId());
            item = itemService.findById(itemId);
            if (member == null || item == null) {
                throw new Exception();
            }
            cart.setMember(member);
            cart.setItem(item);
            cart.setQuantity(1);    // 기본값, 수정 여부 불확실
            cartService.add(cart);
            redirectAttributes.addFlashAttribute("message", "성공적으로 추가되었습니다!");
        } catch (Exception e) {
            System.out.println("CartController.add: 인증 실패");
        }
        return "redirect:/cart";
    }

    @DeleteMapping("/delete/{cartId}")
    @ResponseBody
    public ResponseEntity<?> sub(@PathVariable Long cartId,@SessionAttribute AuthInfo authInfo) {
        Cart cart = cartService.findById(cartId); // 추가 변경필요
        cartService.delete(cart);
        return ResponseEntity.ok("삭제 완료");
    }

}
