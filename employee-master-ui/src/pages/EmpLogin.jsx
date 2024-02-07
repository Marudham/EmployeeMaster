import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import axios from'axios'

export default function EmpLogin() {

  const [ email, setEmail ] = useState('');
  const [ loading, setLoading ] = useState(false)

  const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true)
       try{
        const response = await axios.post("http://localhost:8080/ems/controller/prepareLink", { email });
        if(response.data.status === 'success'){
            document.getElementById("message").innerHTML = "Employee Login Email has been send successfully";
       }
        }
        catch(error){
          console.log(error);
          if (error.response && error.response.data && error.response.data.message) {
            // setMessage(error.response.data.message);
            document.getElementById("message").innerHTML = error.response.data.message;
          } else {
            // setMessage("Unexpected Error has occurred!");
            document.getElementById("message").innerHTML = "Unexpected Error has occurred!";
          }
       }finally{
        setLoading(false)
       }
  }

  return (
    <div className='login-container'>
      <form className='login-form' onSubmit={handleSubmit}>
          <h2>Employee Login</h2>
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
                type="submit"
                disabled={loading}
                value={loading ? 'Preparing Link...' : 'Prepare Link'}
                className='register-submit'
            />
            {loading && <div className="spinner"></div>}
          <p className="new-user-text">
              New User? <Link className='register-link' to={"/register"}>Register</Link>
          </p>
      </form>
   </div>

  )
}
