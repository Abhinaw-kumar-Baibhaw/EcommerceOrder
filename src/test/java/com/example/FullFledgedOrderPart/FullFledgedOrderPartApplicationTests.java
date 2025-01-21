package com.example.FullFledgedOrderPart;

import com.example.FullFledgedOrderPart.entity.CustomerOrder;
import com.example.FullFledgedOrderPart.repo.OrderRepo;
import com.example.FullFledgedOrderPart.serviceImp.OrderServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FullFledgedOrderPartApplicationTests {


	@Mock
	private OrderRepo orderRepo;

	@InjectMocks
	private OrderServiceImplementation orderServiceImplementation;

	private CustomerOrder order;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    public void getAll(){
		order = new CustomerOrder(
				1L,
				"ORD123",
				LocalDateTime.now(),
				BigDecimal.valueOf(200.50),
				"PENDING",
				"123 Street, City",
				1L,
				2L,
				101L,
				null
		);
		List<CustomerOrder> orders = Arrays.asList(order);
		when(orderRepo.findAll()).thenReturn(orders);
		List<CustomerOrder> result = orderServiceImplementation.getAllOrder();
		assertNotNull(result);
	}

	@Test
	public void getOrdersByUserId(){
		Long userId = 1L;
		List<CustomerOrder> mockOrders = Arrays.asList(order);
		when(orderRepo.findUserByUserId(userId)).thenReturn(mockOrders);
		List<CustomerOrder> orders = orderServiceImplementation.getOrdersByUserId(userId);
		assertNotNull(orders);
		assertEquals(1, orders.size());
	}


	@Test
	void contextLoads() {
	}

}
