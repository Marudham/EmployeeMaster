import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'

export default function Register() {

  const [ state, setState ] = useState({
    username: '',
    email: '',
    password: ''
  });

  const [ loading, setLoading ] = useState('');
  const [ showPopup, setShowPopup ] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try{
      const response = await axios.post("http://localhost:8080/ems/controller/register", state);
      if(response.data.status === 'success'){
        console.log("success");
        document.getElementById("message").innerHTML = response.data.message;
        setShowPopup(true);
      }
    }catch(error){
      console.log(error.response.data);
      document.getElementById("message").innerHTML = error.response.data.message;
      setShowPopup(true);
    }finally{
      setLoading(false);
    }
  }

  const closePopup = () => {
    setShowPopup(false);
  }

  return (
    <div className='register-container'>
        <form className='register-form' onSubmit={handleSubmit}>
            <h2>Registration</h2>
            <p className='message' id="message"></p>
            <input
                type="text"
                name="username"
                placeholder="Username"
                value={state.username}
                onChange={(e) => setState({ ...state, username: e.target.value })}
                required
            />
            <input
                type="email"
                name="email"
                placeholder="Email"
                value={state.email}
                onChange={(e) => setState({ ...state, email: e.target.value })}
                required
            />
            <input
                type="password"
                name="password"
                placeholder="Password"
                value={state.password}
                onChange={(e) => setState({ ...state, password: e.target.value })}
                required
            />
            <input
                type="submit"
                disabled={loading}
                value={loading ? 'Registering...' : 'Register'}
                className='register-submit'
            />
            {loading && <div className="spinner"></div>}
            {/* {showPopup && (
                <div className="popup">
                    <p>{document.getElementById("message").innerHTML}</p>
                    <button onClick={closePopup}>Close</button>
                </div>
            )} */}
            <p className='login-link-text'>
                Already Registered? <Link className='login-link' to={"/login"}>Login</Link>
            </p>
        </form>
    </div>

  )
}