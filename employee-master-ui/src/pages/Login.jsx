import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useDispatch } from 'react-redux';
import { login } from '../authSlice';
import axios from'axios'

export default function Login() {

  const [ email, setEmail ] = useState('');
  const [ password, setPassword ] = useState('');
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit = async (e) => {
        e.preventDefault();
       try{
        const response = await axios.post("http://localhost:8080/ems/controller/login", {email, password});
        if(response.data.status === 'success'){
          const response = await axios.get("http://localhost:8080/ems/controller/getAdminId" ,{ params: { email: email},});
          if(response.data != null){
            dispatch(login({ user: { email: email, id: response.data }, role: 'admin' }));
            navigate("/adminHome");
          }else{
            console.log(response.data);
            alert("Login --> null");
          }
        }
       }catch(error){
          console.log(error);
          if (error.response && error.response.data && error.response.data.message) {
            // setMessage(error.response.data.message);
            document.getElementById("message").innerHTML = error.response.data.message;
          } else {
            // setMessage("Unexpected Error has occurred!");
            document.getElementById("message").innerHTML = "Unexpected Error has occurred!";
          }
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
              Login as -<Link className='register-link' to={"/empLogin"}>Employee</Link>
          </p>
          <p className="new-user-text">
              New User? <Link className='register-link' to={"/register"}>Register</Link>
          </p>
      </form>
   </div>

  )
}
