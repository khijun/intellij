package edu.du.sb1031.service;

import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Order;
import edu.du.sb1031.entity.OrderItem;
import edu.du.sb1031.exception.NotEnoughStockException;
import edu.du.sb1031.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemService itemService;

    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public List<OrderItem> saveFromCarts(List<Cart> carts, Order order){
        List<OrderItem> orderItems = new ArrayList<>();
        for (Cart cart : carts) {
            Item item = cart.getItem();
            itemService.subStock(item, cart.getQuantity());
            orderItems.add(save(order, cart.getQuantity(), item));
        }
        return orderItems;
    }

    public OrderItem save(Order order, int quantity, Item item){
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .quantity(quantity)
                .item(item)
                .build();
        return orderItemRepository.save(orderItem);
    }

}
