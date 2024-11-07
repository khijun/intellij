package edu.du.sb1031;

import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.service.CartService;
import edu.du.sb1031.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Sb1031ApplicationTests {

    @Autowired
    MemberService memberService;
    @Autowired
    CartService cartService;

    @Test
    void contextLoads() {
        Member member = memberService.findById(2L);
        System.out.println(member);
    }

    @Test
    void cartService(){
        Member member = memberService.findById(2L);
        Cart cart = cartService.getCart(member);
        System.out.println(cart);
    }

}
