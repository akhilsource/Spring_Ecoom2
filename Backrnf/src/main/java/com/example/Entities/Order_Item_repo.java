package com.example.Entities;
import com.example.Entities.Order_Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_Item_repo extends JpaRepository<Order_Items, String> {
    @Query("SELECT new com.example.Entities.OrderDto(oi.co.id,p.productName, oi.quantity) " +
            "FROM Order_Items oi " +
            "JOIN Product p " +"On p.id=oi.pr.id "+
            "WHERE oi.co.id = :order_id")
    List<OrderDto> findByCo(@Param("order_id") String order_id);
}
