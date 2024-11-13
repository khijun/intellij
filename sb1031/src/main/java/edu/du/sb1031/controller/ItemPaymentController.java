package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.dto.Define;
import edu.du.sb1031.dto.PaymentWrapper;
import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.entity.Delivery;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.service.ItemService;
import edu.du.sb1031.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ItemPaymentController {

    private final ItemService is;
    private final MemberService ms;

    @GetMapping("/item/payment/{id}")
    public String itemPayment(@PathVariable(name = "id") Long itemId, HttpServletRequest request, Model model) {
        try{
            Long memberId = ((AuthInfo) request.getSession().getAttribute("authInfo")).getId();
            System.out.println(memberId);
            Member member = ms.findById(memberId);
            System.out.println(member);
            Item item = is.findById(itemId);
            model.addAttribute("item", item);

            model.addAttribute("orderTotal", item.getPrice());
            model.addAttribute("deliveryCost", Define.DELIVERY); // 임의의 값
            PaymentWrapper pw = new PaymentWrapper();
            pw.getCarts().add(new Cart(null, null, null, 1));
            model.addAttribute("paymentWrapper", pw);
        }catch(Exception e){
            System.out.println("ItemPaymentController.itemPayment: 인증 실패");
        }
        return "/item/payment";
    }
}
