package edu.du.sb1031.service;

import edu.du.sb1031.entity.Delivery;
import edu.du.sb1031.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    public void save(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

}
