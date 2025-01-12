package com.example.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Product_Repo extends JpaRepository<Product, Long> {

    Product findByproductName(String name);
    Product findByid(int id);

    @Query("SELECT new com.example.Entities.CartDTO(c.user.username, p.productName, p.price, c.quantity, c.product.id) " +
            "FROM Cart c JOIN c.product p WHERE c.user.username = :username")
    List<CartDTO> findCartItemsWithProduactDetailsByUsername(@Param("username") String username);
    @Query("SELECT new com.example.Entities.CartDTO(P.productName, P.price, P.id, P.description) FROM Product P ORDER BY P.id DESC LIMIT 10")
    List<CartDTO> fetchItemsforIndex();
    @Query("SELECT c FROM Cart c WHERE c.user.username = :username AND c.product.id = :product_id")
    Cart findCartItemByUsernameAndProductId(@Param("username") String username, @Param("product_id") long product_id);
}
