package com.example.FullFledgedOrderPart.serviceImp;

import com.example.FullFledgedOrderPart.entity.CustomerOrder;
import com.example.FullFledgedOrderPart.entity.ProductInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private static final String ORDER_KEY_PREFIX = "order:"; // Prefix for order keys
    private static final String INVENTORY_KEY_PREFIX = "inventory:"; // Prefix for inventory keys

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void saveOrderToRedis(Long orderId, CustomerOrder order) {
        String key = ORDER_KEY_PREFIX + orderId;
        redisTemplate.opsForValue().set(key, order);
        System.out.println("Order saved to Redis with key: " + key);
    }

    // Retrieve order from Redis
    public CustomerOrder getOrderFromRedis(Long orderId) {
        String key = ORDER_KEY_PREFIX + orderId;
        CustomerOrder order = (CustomerOrder) redisTemplate.opsForValue().get(key);
        if (order != null) {
            System.out.println("Order retrieved from Redis with key: " + key);
        }
        return order;
    }

    // Delete order from Redis
    public void deleteOrderFromRedis(Long orderId) {
        String key = ORDER_KEY_PREFIX + orderId;
        redisTemplate.delete(key);
        System.out.println("Order deleted from Redis with key: " + key);
    }

    // Save product inventory in Redis
    public void saveProductInventoryToRedis(Long productId, ProductInventory inventory) {
        String key = INVENTORY_KEY_PREFIX + productId; // Redis key for product inventory
        redisTemplate.opsForValue().set(key, inventory);
        System.out.println("Product Inventory saved to Redis with key: " + key);
    }

    // Retrieve product inventory from Redis
    public ProductInventory getProductInventoryFromRedis(Long productId) {
        String key = INVENTORY_KEY_PREFIX + productId;
        ProductInventory inventory = (ProductInventory) redisTemplate.opsForValue().get(key);
        if (inventory != null) {
            System.out.println("Product Inventory retrieved from Redis with key: " + key);
        }
        return inventory;
    }

    // Delete product inventory from Redis
    public void deleteProductInventoryFromRedis(Long productId) {
        String key = INVENTORY_KEY_PREFIX + productId;
        redisTemplate.delete(key);
        System.out.println("Product Inventory deleted from Redis with key: " + key);
    }
}

