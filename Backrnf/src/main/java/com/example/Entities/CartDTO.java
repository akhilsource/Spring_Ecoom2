package com.example.Entities;



public class CartDTO {
    private String username;
    private int productId;
    private int product_quantity;
    private String productName;
    private int price;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CartDTO() {
    }
    public CartDTO(String username) {
        this.username = username;
    }
    public CartDTO(int product_id,String username)
    {
        this.username=username;
        this.productId=product_id;
    }
    public CartDTO(String productName,int price,int product_id,String description)
    {
        this.productName=productName;
        this.productId=product_id;
        this.price=price;
        this.description=description;
    }
    public CartDTO(String username, int productId, int product_quantity, String productName, int price) {
        this.username = username;
        this.productId = productId;
        this.product_quantity = product_quantity;
        this.productName = productName;
        this.price = price;
    }
    public CartDTO(String username,String productName, int price,int product_quantity,int productId) {
        this.username = username;
        this.product_quantity = product_quantity;
        this.productName = productName;
        this.price = price;
        this.productId=productId;
    }
    public CartDTO(String username, int productId, int product_quantity) {
        this.username = username;
        this.productId = productId;
        this.product_quantity = product_quantity;
    }
    // Getters and Setters
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProduct_quantity() {
        return this.product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "username='" + username + '\'' +
                ", productId=" + productId +
                ", quantity=" + product_quantity +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}

