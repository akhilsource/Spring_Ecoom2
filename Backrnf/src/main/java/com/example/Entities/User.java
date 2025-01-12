package com.example.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users", indexes = {@Index(name = "idx_username", columnList = "username")})
public class User {

    // Default constructor
    public User() {
        // Removed unnecessary System.out.print statement
    }

    // Constructor with username
    public User(String username) {
        this.username = username;
    }

    // Constructor with username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username; // Make username the primary key
    @Column(name = "password")
    private String password;
    // Getter and Setter for Username (Primary Key)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
