package com.example.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false,unique = true)
    private int id;
    @Column(name = "product_name", nullable = false)
    @NotNull(message = "Product name cannot be null")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String productName;
    @Column(name = "price", nullable = false)
    @Min(value = 0, message = "Price cannot be negative")
    private int price;

    @Column(name = "description", length = 500)
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Column(name = "image_path", length = 255)
    @Size(max = 255, message = "Image path must not exceed 255 characters")
    private String imagePath;

    // Default Constructor
    public Product() {
    }

    // Constructor without ID (for creating new objects)
    public Product(String productName, int price, String description, String imagePath) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Constructor with ID (for updates)
    public Product(int id, String productName, int price, String description, String imagePath) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // toString, equals, and hashCode
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
