import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import axios from'axios'

export default function Login() {

  const [ email, setEmail ] = useState('');
  const [ password, setPassword ] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
        e.preventDefault();
       try{
        const response = await axios.post("http://localhost:8080/ems/controller/login", {email, password});
        console.log(response.data);
        if(response.data.status === 'success'){
          //redirect to adminHome component
          navigate("/adminHome");
        }
       }catch(error){
        console.log(error.response.data);
        document.getElementById("message").innerHTML = error.response.data.message;
       }
  }

  return (
    <div className='login-container'>
      <form className='login-form' onSubmit={handleSubmit}>
          <h2>Login</h2>
          <p id='message'></p>
          <input
              type="email"
              name="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
          />
          <input
              type="password"
              name="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
          />
          <Link className='forgot-password-link' to={"/forgotPassword"}>
              Forgot Password ?
          </Link>
          <input type="submit" value="Login" className='login-submit' />
          <p className="new-user-text">
              New User? <Link className='register-link' to={"/register"}>Register</Link>
          </p>
      </form>
   </div>

  )
}
