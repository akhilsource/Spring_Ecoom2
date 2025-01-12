package com.example.Entities;
public class OrderRequest {

    // Fields
    public OrderRequest()
    {

    }
    private String customerUsername; // Renamed from 'username'
    private long productId; // Kept as 'productId'
    private int quantity; // Renamed from 'orderItemQuantity'

    // Constructor
    public OrderRequest(String customerUsername, long productId, int quantity) {
        this.customerUsername = customerUsername;
        this.productId = productId;
        this.quantity = quantity;
    }
    // Getters and Setters
    public String getCustomerUsername() {
        return customerUsername;
    }
    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
