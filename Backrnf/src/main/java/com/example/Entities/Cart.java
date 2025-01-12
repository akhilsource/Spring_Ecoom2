package com.example.Entities;
import jakarta.persistence.*;

@Entity
@Table(name="Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for the Cart table
    @ManyToOne
    @JoinColumn(name = "username_cart", referencedColumnName = "username", nullable = false)
    private User user; // Foreign key referencing User table's username

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product; // Foreign key referencing Product table's product_id
    @Column(name = "quantity", nullable = false)
    private int quantity; // Quantity of the product in the cart
    // Default constructor
    public Cart() {}
    // Constructor
    public Cart(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

