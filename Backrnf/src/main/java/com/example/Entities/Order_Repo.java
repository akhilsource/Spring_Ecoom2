package com.example.Entities;

import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Order_Repo extends JpaRepository<CustomerOrder,String> {
    @Query("SELECT o.orderId FROM CustomerOrder o WHERE o.user.username = :userId")
    List<String> findAllOrderIdsByUserId(@Param("userId") String userId);
}
