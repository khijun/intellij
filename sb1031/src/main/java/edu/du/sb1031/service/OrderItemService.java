package edu.du.sb1031.service;

import edu.du.sb1031.entity.OrderItem;
import edu.du.sb1031.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

}
