import { useState } from 'react';
import './Signup.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function Signup() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmUsername, setConfirmUsername] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState({ username: false, password: false, message: '' });
    const navigate = useNavigate();

    const handleSignup = async(e) => {
        e.preventDefault();
        setError({ username: false, password: false, message: '' });

        // Basic validation
        if (username !== confirmUsername) {
            setError({ username: true, password: false, message: "Usernames do not match." });
            return;
        }
        if (password !== confirmPassword) {
            setError({ username: false, password: true, message: "Passwords do not match." });
            return;
        }

        try {
            const response = await axios.post("http://127.0.0.1:8080/users/signup", {
                username: username,
                password: password,
            });

            if (response.status === 200) {
                navigate('/login');
            }
        } catch (error) {
            if (error.response && error.response.status === 401) {
                setError({...error, message: "Username already Exists." });
            } else {
                setError({...error, message: "An unexpected error occurred. Please try again." });
            }
        }
    };

    return ( <
        div className = "body-signup" >
        <
        div className = "Signup-Container" >
        <
        div className = "Signup-box" >
        <
        h2 > Signup < /h2> {
        error.message && < p className = "error-message" > { error.message } < /p>} <
        form onSubmit = { handleSignup } >
        <
        div className = "input-group" >
        <
        label > Username < /label> <
        input type = "text"
        value = { username }
        onChange = {
            (e) => setUsername(e.target.value)
        }
        required /
        >
        <
        /div> <
        div className = "input-group" >
        <
        label > Confirm Username < /label> <
        input type = "text"
        value = { confirmUsername }
        onChange = {
            (e) => setConfirmUsername(e.target.value)
        }
        required style = {
            { borderColor: error.username ? 'red' : '' }
        }
        /> < /
        div > <
        div className = "input-group" >
        <
        label > Password < /label> <
        input type = "password"
        value = { password }
        onChange = {
            (e) => setPassword(e.target.value)
        }
        required style = {
            { borderColor: error.password ? 'red' : '' }
        }
        /> < /
        div > <
        div className = "input-group" >
        <
        label > Confirm Password < /label> <
        input type = "password"
        value = { confirmPassword }
        onChange = {
            (e) => setConfirmPassword(e.target.value)
        }
        required /
        >
        <
        /div> <
        button type = "submit"
        className = "signup-button" > Signup < /button> < /
        form > <
        /div> < /
        div > <
        /div>
    );
}
export default Signup;