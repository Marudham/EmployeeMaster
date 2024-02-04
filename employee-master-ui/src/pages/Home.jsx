import React from 'react'
import { Link } from 'react-router-dom';

export default function Home() {
  return (
    <div className='home-container'>
      <h3 className='home-title'>EmployeeMaster</h3>
      <div className='home-links'>
          <Link className='home-link' to={"/login"}>Login</Link>
          <Link className='home-link' to={"/register"}>Register</Link>
      </div>
    </div>
  )
}
