package com.example.FullFledgedOrderPart.entity;



public class ProductInventory {

    private Long productId;

    private Long quantity;

    ProductInventory(){

    }

    public ProductInventory(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
