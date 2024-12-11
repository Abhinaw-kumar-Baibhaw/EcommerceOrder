package com.example.FullFledgedOrderPart.service;


import com.example.FullFledgedOrderPart.entity.CustomerOrder;
import com.example.FullFledgedOrderPart.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

     CustomerOrder createOrder(CustomerOrder customerOrder);

     List<CustomerOrder> getAllOrder();

      Optional<CustomerOrder> getById(Long userId);

    List<CustomerOrder> getOrdersByUserId(Long userId);
}


