package com.example.FullFledgedOrderPart.repo;

import com.example.FullFledgedOrderPart.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepo extends JpaRepository<CustomerOrder,Long> {

    List<CustomerOrder> findUserByUserId(Long userId);
}
