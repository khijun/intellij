package edu.du.sb1031.event;

import edu.du.sb1031.entity.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderEvent extends ApplicationEvent {
    private Order order;
    private String memberId;
    private String itemId;

    public OrderEvent(Object source, Order order, String memberId, String itemId) {
        super(source);
        this.order = order;
        this.memberId = memberId;
        this.itemId = itemId;
    }

}
