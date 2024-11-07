package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.entity.Order;
import edu.du.sb1031.entity.OrderItem;
import edu.du.sb1031.repository.OrderItemRepository;
import edu.du.sb1031.service.ItemService;
import edu.du.sb1031.service.MemberService;
import edu.du.sb1031.service.OrderItemService;
import edu.du.sb1031.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ItemService itemService;
    private final OrderItemService oiService;
    private final MemberService memberService;

    @GetMapping("{itemId}")
    public String order(@PathVariable Long itemId, Model model, HttpSession session) {
        List<OrderItem> orderItems = new ArrayList<>();
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("order", new Order());
        return "/order/orderForm";
    }

    @PostMapping("add")
    public String add(Order order, Model model){
        order.getOrderItems().add(null);
        return "/order/orderForm";
    }

    @PostMapping
    public String save(Order order, HttpSession session) {
        Long memberId = ((AuthInfo) session.getAttribute("authInfo")).getId();
        Member member = memberService.findById(memberId);
        order.setMember(member);

        orderService.save(order);

        order.getOrderItems().forEach(orderItem ->
                orderItem.setOrder(order));
        order.getOrderItems().forEach(oiService::save);

        System.out.println("주문: "+order);
        return "/order/orderSuccess";
    }
}