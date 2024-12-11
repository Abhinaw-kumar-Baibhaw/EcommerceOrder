package com.example.FullFledgedOrderPart.controller;

import com.example.FullFledgedOrderPart.entity.CustomerOrder;
import com.example.FullFledgedOrderPart.entity.User;
import com.example.FullFledgedOrderPart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/getUserOrders/{userId}")
    public List<CustomerOrder> getUserOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @PostMapping("/create")
    public CustomerOrder create(@RequestBody CustomerOrder customerOrder){
        System.out.println(customerOrder.getOrderDate());
       return orderService.createOrder(customerOrder);
    }

    @GetMapping("/allOrder")
    public List<CustomerOrder> allOrder(){
        return orderService.getAllOrder();
    }

    @GetMapping("/getById/{id}")
    public Optional<CustomerOrder> getById(@PathVariable("id") Long id){
       return orderService.getById(id);
    }

}
