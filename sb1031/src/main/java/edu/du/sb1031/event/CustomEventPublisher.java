package edu.du.sb1031.event;

import edu.du.sb1031.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishOrderCreatedEvent(final Order order, final String memberId, final String itemId) {
        System.out.println("Publishing custom event");
        OrderEvent event = new OrderEvent(this, order, memberId, itemId);
        publisher.publishEvent(event);
    }
}
