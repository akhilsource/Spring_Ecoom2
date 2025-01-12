import React, { useState } from 'react';
import './Login.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Login_signup = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState({ username: false, password: false }); // Error state to track invalid inputs
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault(); 
    setError({ username: false, password: false });
    try {
      const response = await axios.post("http://127.0.0.1:8080/users/login", {
        username: username,
        password: password,
      });

      console.log(response.data); 
      if (response.status === 200) {
        const token = response.data.split('Token: ')[1];
        sessionStorage.clear();
        sessionStorage.setItem('user',username); 
        sessionStorage.setItem('authToken', token);
        navigate('/');
      } 
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setError({ username: true, password: true });
      } else {
        alert('An error occurred. Please try again later.');
      }
    }
  };
  const handle_signup = () => {
    navigate('/signup');
  };

  const handleGoogleLogin = () => {
    console.log('Logging in with Google');
  };

  return (
    <div className="login_signup-body">
      <div className="login_signup-container">
        <div className="login_signup-box">
          <h2>Login</h2>
          <form onSubmit={handleSubmit}>
            <div className="login_signup-input-group">
              <label>Username</label>
              <input
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
                style={{ borderColor: error.username ? 'red' : '' }} // Apply red border if error
              />
            </div>
            <div className="login_signup-input-group">
              <label>Password</label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                style={{ borderColor: error.password ? 'red' : '' }} // Apply red border if error
              />
            </div>
            <button type="submit" className="login_signup-login-button">Login</button>
          </form>
          <div className="login_signup-or-divider">OR</div>
          <button onClick={handleGoogleLogin} className="login_signup-google-login-button">
            Login with Google
          </button>
          <div className="login_signup-or-divider">OR</div>
          <button onClick={handle_signup} className="login_signup-signup-button">Signup</button>
        </div>
      </div>
    </div>
  );
};

export default Login_signup;
