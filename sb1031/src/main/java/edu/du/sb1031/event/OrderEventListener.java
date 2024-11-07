package edu.du.sb1031.event;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.service.ItemService;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.service.MemberService;
import edu.du.sb1031.entity.Order;
import edu.du.sb1031.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.persistence.EntityManagerFactory;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener {
    private final ItemService itemService;
    private final OrderService orderService;
    private final MemberService memberService;
    private final EntityManagerFactory emf;

    @EventListener
    public void handleContextStart(OrderEvent event) {
//        Order order = event.getOrder();
//        Member member = memberService.findById(Long.valueOf(event.getMemberId()));
//        order.setMember(member);
//        orderService.saveOrder(order);
//        Item item = itemService.findById(Long.valueOf(event.getItemId()));
//        Shipment shipment = Shipment.builder()
//                .status("배송중")
//                .order(order)
//                .item(item)
//                .build();
//        shipmentService.saveShipment(shipment);
//        order.setShipment(shipment);
    }
}
