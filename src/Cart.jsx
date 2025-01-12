import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Cart.css";
const Cart = () => {
  const navigate = useNavigate();
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const username = sessionStorage.getItem("user");
  const token = sessionStorage.getItem("authToken");

  const removeItem = async (itemId) => {
    console.log("Removing item with ID:", itemId);
    try {
      const response = await axios.post(
        "http://localhost:8080/products/remove_cart",
        { username, CartItems: itemId },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.status === 200) window.location.reload();
    } catch (Exception) {}
  };

  const buyOutCart = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/orders/buy_cart",
        {username},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.status === 200) {
        alert("Purchase successful!");
        window.location.reload(); // Refresh the page after successful purchase
      }
    } catch (err) {
      console.error("Error during checkout:", err);
      setError("Failed to process your order. Please try again later.");
    }
  };

  useEffect(() => {
    if (!username) {
      navigate("/login");
    } else {
      const fetchCartItems = async () => {
        try {
          const response = await axios.post(
            "http://localhost:8080/products/get_all_cart",
            { username },
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          );
          setCartItems(response.data);
        } catch (err) {
          navigate("/login");
          setError("Failed to load cart items. Please try again later.");
        } finally {
          setLoading(false);
        }
      };
      fetchCartItems();
    }
  }, [navigate]);

  // Calculate the total price of the cart
  const calculateTotal = () => {
    return cartItems.reduce((total, item) => total + item.price * item.product_quantity, 0);
  };

  if (loading) return <p>Loading your cart...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="cart-container">
      <h1>Your Cart</h1>
      {cartItems.length > 0 ? (
        <>
          <ul className="cart-list">
            {cartItems.map((item) => (
              <li key={item.Id} className="cart-item">
                <img
                  src={item.image} // Image URL from backend
                  alt={item.name}
                  className="cart-item-image"
                />
                <div className="cart-item-details">
                  <p className="cart-item-name">{item.productName}</p>
                  <p>Price: ${item.price}</p>
                  <p>Quantity: {item.product_quantity}</p>
                  <button onClick={() => removeItem(item.productId)}>Remove</button>
                </div>
              </li>
            ))}
          </ul>
          <div className="cart-total">
            <h3>Total: ${calculateTotal()}</h3>
            <button onClick={buyOutCart} className="buy-out-button">
              Buy Now
            </button>
          </div>
        </>
      ) : (
        <p>Your cart is empty.</p>
      )}
    </div>
  );
};
export default Cart;
