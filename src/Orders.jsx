import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Orders.css';

const Orders = () => {
  const navigate = useNavigate();
  const [orders, setOrders] = useState(null); // Stores the fetched orders
  const [loading, setLoading] = useState(true); // Tracks loading state
  const [error, setError] = useState(null); // Stores any errors

  const authToken = sessionStorage.getItem('authToken'); // Directly retrieving token from sessionStorage
  const username = sessionStorage.getItem('user'); // Directly retrieving username from sessionStorage

  // Redirect to login if username is not available
  useEffect(() => {
    if (!username) {
      navigate("/login");
    } else {
      const fetchOrders = async () => {
        try {
          const response = await axios.post(
            "http://localhost:8080/orders/retrieve_items",
            { username },
            {
              headers: {
                Authorization: `Bearer ${authToken}`,
              },
            }
          );
          // Set the orders state after a successful response
          setOrders(response.data);
        } catch (err) {
          setError("Failed to load orders. Please try again later.");
          navigate("/login");
        } finally {
          setLoading(false);
        }
      };

      fetchOrders();
    }
  }, [navigate, authToken, username]);

  if (loading) {
    return <p className="loading-message">Loading your orders...</p>; // Show loading message
  }

  if (error) {
    return <p className="error-message">{error}</p>; // Show error message if any
  }

  return (
    <div className="orders-container">
      <div className="Order-heading">
      <h1 className="orders-title">Your Orders</h1>
      </div>
      {orders && orders.length > 0 ? (
        <div className="orders-list">
          {orders.map((order, index) => (
            <div className="order-card" key={index}>
              <div className="order-image-container">
                <img
                  src="https://via.placeholder.com/150" // Placeholder for product image
                  alt={order.product_name}
                  className="order-image"
                />
              </div>
              <div className="order-details">
                <h2 className="order-name">{order.product_name}</h2>
                <p><strong>Order ID:</strong> {order.order_item_ids}</p>
                <p><strong>Quantity:</strong> {order.order_item_quantity}</p>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p className="no-orders-message">No orders found.</p> // If no orders are fetched
      )}
    </div>
  );
};

export default Orders;
