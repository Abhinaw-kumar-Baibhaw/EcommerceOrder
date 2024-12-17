package com.example.FullFledgedOrderPart.serviceImp;

import com.example.FullFledgedOrderPart.entity.CustomerOrder;
import com.example.FullFledgedOrderPart.entity.ProductInventory;
import com.example.FullFledgedOrderPart.exception.NoEnoughInventoryException;
import com.example.FullFledgedOrderPart.repo.OrderRepo;
import com.example.FullFledgedOrderPart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

   public OrderServiceImplementation(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String ORDER_TOPIC = "order-created";

    public CustomerOrder createOrder(CustomerOrder customerOrder) {
        Optional<CustomerOrder> byId = orderRepo.findById(customerOrder.getUserId());
        if (byId.isPresent()) {
            CustomerOrder customerOrder1 = byId.get();
            String url = "http://FULLFLEDGEDINVENTORYPART/inventory/getById/" + customerOrder1.getProductId();
            ProductInventory product = restTemplate.getForObject(url, ProductInventory.class);
            Long quantity = product.getQuantity();
            Long quantity1 = customerOrder1.getQuantity();
            if (product.getProductId() == customerOrder1.getProductId()) {
                Long actualQuantity = quantity - quantity1;
                product.setQuantity(actualQuantity);
                restTemplate.put("http://FULLFLEDGEDINVENTORYPART/inventory/update/" + customerOrder.getProductId(),
                        new ProductInventory(product.getProductId(), actualQuantity));

                String orderMessage = "Order Created: UserId=" + customerOrder1.getUserId() +
                        ", ProductId=" + customerOrder1.getProductId() +
                        ", Quantity=" + customerOrder1.getQuantity() +
                        ", Remaining Quantity=" + actualQuantity;
                kafkaTemplate.send(ORDER_TOPIC, orderMessage);
                System.out.println(orderMessage);

            } else {
                throw new NoEnoughInventoryException("Not enough inventory for productId " + customerOrder.getProductId());
            }
        }
        return customerOrder;
    }


    @Override
    public List<CustomerOrder> getAllOrder() {
        return orderRepo.findAll();
    }

    @Override
    public Optional<CustomerOrder> getById(Long userId) {
        return orderRepo.findById(userId);
    }

    @Override
    public List<CustomerOrder> getOrdersByUserId(Long userId) {
        return orderRepo.findUserByUserId(userId);
    }

}
