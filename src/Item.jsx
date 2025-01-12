import React, { useState } from "react";
import axios from "axios";
import "./Item.css";
import { useNavigate } from "react-router-dom";

const Item = ({ imageaddress, image_name, price, rating,product_id }) => {
    const navigate = useNavigate();
    const remove_item=async (itemId) =>{
        
    }
    const [quantity, setQuantity] = useState(1);
    const handleIncreaseQuantity = () => setQuantity((prev) => prev + 1);
    const handleDecreaseQuantity = () => {
        if (quantity > 1) setQuantity((prev) => prev - 1);
    };
    const addToCart = async () => {
        const username = sessionStorage.getItem("user");
        const token = sessionStorage.getItem("authToken");
        try {
            const response = await axios.post(
                "http://localhost:8080/products/add_to_cart",
                {
                    username,
                    productId: product_id,
                    product_quantity: quantity,
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
            alert("Item added to cart successfully!");
        } catch (error) {
            console.error("Failed to add item to cart:", error);
            if (error.response?.status === 403) {
                alert("Session expired. Please log in again.");
                navigate("/login");
            } else {
                alert("Something went wrong. Please try again.");
            }
        }
    };

    const buyNow = async() => {
        const username = sessionStorage.getItem("user");
        const token = sessionStorage.getItem("authToken");
        try {
            const response = await axios.post(
                "http://localhost:8080/orders/place-order",
                {
                    customerUsername:username,
                    productId: product_id,
                    quantity: quantity,
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
            alert("Item Bought succesfully!");
        } catch (error) {
            console.error("Failed to Buy:", error);
            if (error.response?.status === 403) {
                alert("Session expired. Please log in again.");
                navigate("/login");
            } else {
                alert("Something went wrong. Please try again.");
            }
        }
    };
    return (
        <div className="item-card-container">
            <div className="item-card-image-container">
                <img src={imageaddress} alt={image_name} />
            </div>
            <div className="item-card-details-container">
                <div className="item-card-name">Name: {image_name}</div>
                <div className="item-card-price">Price: ${price}</div>
                <div className="item-card-rating">Rating: {rating}</div>
                <div className="item-card-quantity-container">
                    <button
                        className="quantity-button"
                        onClick={handleDecreaseQuantity}
                        disabled={quantity === 1}
                    >
                        -
                    </button>
                    <span className="quantity-value">{quantity}</span>
                    <button className="quantity-button" onClick={handleIncreaseQuantity}>
                        +
                    </button>
                </div>
            </div>
            <div className="item-card-actions-container">
                <button className="add-to-cart-button" onClick={addToCart}>
                    Add to Cart
                </button>
                <button className="buy-now-button" onClick={buyNow}>
                    Buy Now
                </button>
            </div>
        </div>
    );
};

export default Item;
