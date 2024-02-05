import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axios from'axios'

export default function Login() {

  const [ email, setEmail ] = useState('');
  const [ password, setPassword ] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
        e.preventDefault();
       try{
        const response = await axios.post("http://localhost:8080/ems/controller/superAdminLogin", {email, password});
        console.log(response.data);
        if(response.data.status === 'success'){
          navigate("/SAHome");
        }
       }catch(error){
        console.log(error.response.data);
        document.getElementById("sa-message").innerHTML = error.response.data.message;
       }
  }

  return (
    <div className='login-container'>
      <form style={{backgroundColor:'tomato'}} className='login-form' onSubmit={handleSubmit}>
          <h2>Super Admin Login</h2>
          <p id='sa-message'></p>
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
          <input type="submit" value="Login" className='login-submit' />
       
      </form>
   </div>

  )
}
