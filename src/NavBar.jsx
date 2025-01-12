import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Navbar.css';

function NavBar() {
    const navigate = useNavigate();

    const handleLog = () => {
        navigate('/login');
    };

    const handleCart = () => {
        navigate('/cart'); // Navigate to the cart page
    };

    return (
        <nav className="navbar">
            <div className="navbar-container">
                <h1 className="logo">Ecom</h1>
                <div className="search-container">
                    <input
                        type="text"
                        className="search-bar"
                        placeholder="Search..."
                    />
                    <button className="search-button">Search</button>
                </div>
                <div className="nav-buttons">
                    <button className="login-button" onClick={handleLog}>
                        Login
                    </button>
                    <button className="cart-button" onClick={handleCart}>
                        {/* Using Bootstrap Cart Icon */}
                        <i className="bi bi-cart cart-icon"></i>
                    </button>
                    <button className="myorders-button" onClick={() => navigate('/myorders')}>
                        My Orders
                    </button>
                </div>
            </div>
        </nav>
    );
}

export default NavBar;
