import React, { useEffect, useState } from 'react';
import Navbar from './Navbar';
import Carousal from './Carousal';
import Item from './Item';
import './index.css';

function Index() {
    const [items, setItems] = useState([]);
    const image_array = [
        "/images/shopping.jpeg",
        "/images/bluetooth.jpeg",
        "/images/Laptop_backpack.jpeg",
        "/images/smart_watch.jpeg",
        "/images/wireless.jpeg",
        "/images/image6.jpeg"
    ]; // Array of image addresses for 6 items

    // Fetch items from the database
    useEffect(() => {
        const fetchItems = async () => {
            try {
                const response = await fetch('http://localhost:8080/products/intro_fetch'); // Replace '/api/items' with your API endpoint
                const data = await response.json();
                setItems(data); 
            } catch (error) {
                console.error('Error fetching items:', error);
            }
        };

        fetchItems();
    }, []);

    return (
        <>
            <div className="Index-Body">
                <div className="Index-navbar-Container">
                    <Navbar />
                </div>
                <div className="Index-Carousel">
                    <Carousal />
                </div>
                <div className="Index-Recently-uploaded">
                    <h1>Things U might Like</h1>
                </div>
                <div className="Index-product">
                    {items.map((item, index) => (
                        <Item
                            key={item.productId} // Ensure 'id' or unique identifier is available in the item object
                            imageaddress={image_array[index % image_array.length]} // Use index to fetch corresponding image address
                            image_name={item.productName}
                            price={item.price}
                            rating={item.rating}
                            product_id={item.productId}
                        />
                    ))}
                </div>
            </div>
        </>
    );
}

export default Index;
