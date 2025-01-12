package com.example.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class Order_Items {
    public Order_Items(long id, CustomerOrder co, Product pr, int quantity) {
        this.id = id;
        this.co = co;
        this.pr = pr;
        this.quantity = quantity;
    }
    public Order_Items(CustomerOrder co, Product pr, int quantity) {
        this.co = co;
        this.pr = pr;
        this.quantity = quantity;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @ManyToOne
    @JoinColumn(name="order_item_ids",referencedColumnName = "order_id")
    private CustomerOrder co;
    @ManyToOne
    @JoinColumn(name="product_id",referencedColumnName = "id")
    private Product pr;
    @Column(name="order_item_quantity")
    private int quantity;
}
