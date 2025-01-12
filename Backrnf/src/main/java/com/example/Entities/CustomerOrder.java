package com.example.Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "orders")
public class CustomerOrder {
    public  CustomerOrder()
    {

    }
    @Id
    @Column(name="order_id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "username")
    private User user;
    @Column(name="status")
    private String status;
    @Column(name="date")
    private Date orderDate;

    public CustomerOrder(String orderId, User user, String status, Date orderDate) {
        this.orderId = orderId;
        this.user = user;
        this.status = status;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
// Getters and setters
}
