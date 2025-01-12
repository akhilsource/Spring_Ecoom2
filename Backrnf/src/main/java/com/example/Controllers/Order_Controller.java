package com.example.Controllers;

import com.example.Entities.*;
import com.example.Servicies.Order_Id_Genration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Entities.Product;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class Order_Controller {
    @Autowired
    private UserRepository ur;
    @Autowired
    private Cart_Repo cart;
    @Autowired
    private Order_Repo or1;
    @Autowired
    private Order_Item_repo ori;
    @Autowired
    private Product_Repo pr;
    @Autowired
    private Order_Id_Genration oi;

    // Endpoint to buy all items from the cart
    @PostMapping("/buy_cart")
    private ResponseEntity<?> buyCart(@RequestBody User user) {
        try {
            // Fetch all cart items for the user
            List<CartDTO> result = pr.findCartItemsWithProduactDetailsByUsername(user.getUsername());
            String username = user.getUsername();
            String order_id = oi.generateOrderId(username);
            java.util.Date currentDate = new java.util.Date();

            // Create a new order
            CustomerOrder temp = new CustomerOrder(order_id, user, "Ordered", currentDate);
            or1.save(temp);

            // Create order items for each cart item
            for (CartDTO cd1 : result) {
                Product product = pr.findByid(cd1.getProductId());
                ori.save(new Order_Items(temp, product, cd1.getProduct_quantity()));
            }

            // Clear the user's cart
            cart.clear_all(username);

            return ResponseEntity.ok("Order placed successfully for all cart items!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while placing order: " + e.getMessage());
        }
    }

    // Endpoint to buy a single item
    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            // Step 1: Generate a new order ID for the customer
            System.out.println(orderRequest.getCustomerUsername()+" "+orderRequest.getProductId()+" "+orderRequest.getQuantity());
            String generatedOrderId = oi.generateOrderId(orderRequest.getCustomerUsername());
            Date currentDate = new Date();
          System.out.println(orderRequest.getCustomerUsername()+" "+orderRequest.getProductId()+" "+orderRequest.getQuantity());
            // Step 2: Fetch the customer (user) by username
            User customer = ur.findByUsername(orderRequest.getCustomerUsername());
            if (customer == null) {
                return ResponseEntity.badRequest().body("Customer not found.");
            }
            // Step 3: Create and save a new CustomerOrder
            CustomerOrder customerOrder = new CustomerOrder(generatedOrderId, customer, "Ordered", currentDate);
            or1.save(customerOrder);

            // Step 4: Fetch the product by its ID
            Product pro = pr.findByid((int)orderRequest.getProductId());
            if (pro == null) {
                return ResponseEntity.badRequest().body("Product not found.");
            }

            // Step 5: Create and save a new OrderItem entry
            Order_Items orderItem = new Order_Items(customerOrder, pro, orderRequest.getQuantity());
            ori.save(orderItem);
            // Step 6: Return a success response
            return ResponseEntity.ok("Order placed successfully for product ID: " + orderRequest.getProductId());
        } catch (Exception exception) {

            return ResponseEntity.badRequest().body("Error while placing order: " + exception.getMessage());
        }
    }

    @PostMapping("/retrieve_items")
    public ResponseEntity<?> retrieveOrderItems(@RequestBody User user) {
        try { System.out.println(user.getUsername());
            List<String> orderIds = or1.findAllOrderIdsByUserId(user.getUsername());
            User u1=ur.findByUsername(user.getUsername());
            if(u1==null)
                System.out.println("null");
            List<OrderDto> allOrderItems = new ArrayList<>();
            for (String orderId : orderIds) {
                List<OrderDto> items = ori.findByCo(orderId); // Fetch items for the given order ID
                allOrderItems.addAll(items); // Add all items to the single list
            }

            return ResponseEntity.ok(allOrderItems);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}