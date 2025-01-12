import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Signup from './Signup';
import Login_signup from './Login_signup';
import Index from './Index';
import Item from './Item';
import Cart from './Cart';
// Importing the image for proper resolution
import CameraImage from '/images/download.jpeg';
import Orders from './Orders';
const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Index />} />
        <Route
          path="/item"
          element={
            <Item
              imageaddress={CameraImage}
              image_name="Camera"
              price="90000"
              rating="10"
              id="1"
            />
          }
        />
        <Route path="/login" element={<Login_signup />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/myorders" element={<Orders />} />
      </Routes>
    </Router>
  );
};

export default App;
