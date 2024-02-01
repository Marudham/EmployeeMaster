import React from 'react'
import { Link } from 'react-router-dom';
import '../styles/Home.css'

export default function Home() {
  return (
    <div className='container'>
        <h3 className='title'>EmployeeMaster</h3>
        <Link className='link' to={"/login"}>Login</Link>
        <Link className='link' to={"/register"}>Register</Link>
    </div>
  )
}
