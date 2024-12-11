package com.example.FullFledgedOrderPart.serviceImp;

import com.example.FullFledgedOrderPart.entity.CustomerOrder;
import com.example.FullFledgedOrderPart.repo.OrderRepo;
import com.example.FullFledgedOrderPart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public CustomerOrder createOrder(CustomerOrder customerOrder) {
      return orderRepo.save(customerOrder);
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
