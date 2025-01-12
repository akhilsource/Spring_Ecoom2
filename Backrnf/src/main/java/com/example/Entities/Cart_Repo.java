package com.example.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface Cart_Repo  extends JpaRepository<Cart,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.user.username = :username AND c.product.id = :product_id")
    int remove_Cart(@Param("product_id") long product_id, @Param("username") String username);
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.user.username = :username")
    int clear_all( @Param("username") String username);
}
