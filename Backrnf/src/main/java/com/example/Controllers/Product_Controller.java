package com.example.Controllers;
import com.example.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class Product_Controller {
    @Autowired
    private Product_Repo pr;
    @Autowired
    private Cart_Repo cr;
    @Autowired
    private UserRepository ur;
    @PostMapping("/add_to_cart")
    public ResponseEntity<?> add_item(@RequestBody CartDTO request) {
        System.out.println(request.getProduct_quantity() + "ffff");

        User u1 = ur.findByUsername(request.getUsername());
        Product p1 = pr.findByid(request.getProductId());
        if (p1 == null) {
            return ResponseEntity.badRequest().body("Product not found");
        }

        // Check if the item already exists in the cart for the user
        Cart existingCartItem = pr.findCartItemByUsernameAndProductId(request.getUsername(), request.getProductId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getProduct_quantity());
            cr.save(existingCartItem); // Save the updated cart item
            return ResponseEntity.ok().body("Cart updated successfully");
        } else {
            // If the item doesn't exist, create a new cart item
            Cart newCartItem = new Cart(u1, p1, request.getProduct_quantity());
            cr.save(newCartItem); // Save the new cart item
            return ResponseEntity.ok().body("Item added to cart successfully");
        }
    }

    @GetMapping("/intro_fetch")
    public ResponseEntity<List<CartDTO>> fetch_()
    {
        List<CartDTO>lr=pr.fetchItemsforIndex();
        return ResponseEntity.ok(lr);
    }
    @PostMapping("/remove_cart")
    public ResponseEntity<?>remove_Cart(@RequestBody CartDTO product)
    {
        String username= product.getUsername();
        long id=(long)product.getProductId();
        int result=cr.remove_Cart(id,username);
        return ResponseEntity.ok("done");
    }
    @PostMapping("/get_all_cart")
    public ResponseEntity<List<CartDTO>>  Get_items(@RequestBody CartDTO username)
    {
            List<CartDTO> result = pr.findCartItemsWithProduactDetailsByUsername(username.getUsername());
            for(CartDTO  obj:result)
                System.out.println(obj.getUsername()+" "+obj.getProductName());
            return ResponseEntity.ok(result);
    }
    @PostMapping("/buy_cart_item")
    public ResponseEntity<?>buy_item(@RequestBody CartDTO username)
    {
        List<CartDTO>list=pr.findCartItemsWithProduactDetailsByUsername(username.getUsername());
        return  ResponseEntity.ok("done");
    }
}
