package edu.du.sb1031;

import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.repository.ItemRepository;
import edu.du.sb1031.service.CartService;
import edu.du.sb1031.service.ItemService;
import edu.du.sb1031.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Sb1031ApplicationTests {

    @Autowired
    MemberService memberService;
    @Autowired
    CartService cartService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    @Test
    void contextLoads() {
        Member member = memberService.findById(2L);
        System.out.println(member);
    }

    @Test
    void cartService(){
        Member member = memberService.findById(2L);
    }

    @Test
    void itemServiceSearchTest(){
        String type = "mostSell";
        List<Item> items = itemService.findByType(type);
        System.out.println(type);
        items.forEach(System.out::println);

        type = "lowPrice";
        items = itemService.findByType(type);
        System.out.println(type);
        items.forEach(System.out::println);

        type = "highPrice";
        items = itemService.findByType(type);
        System.out.println(type);
        items.forEach(System.out::println);

        type = "newest";
        items = itemService.findByType(type);
        System.out.println(type);
        items.forEach(System.out::println);

        type = "mostReviewed";
        items = itemService.findByType(type);
        System.out.println(type);
        items.forEach(System.out::println);

    }

}
