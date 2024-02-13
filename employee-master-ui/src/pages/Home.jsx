import React, { useState } from 'react'
import { Link } from 'react-router-dom';

export default function Home() {

  const [ showAdmin, setShowAdmin ] = useState(false)

  const handleClosePopup = () => {
    setShowAdmin(false);
  };

  return (
    <div className='home-container'>
      <h3 className='home-title'>EmployeeMaster</h3>
      <div className='home-links'>
          <Link className='home-link home-link-admin' onClick={ () => setShowAdmin(true)}>Admin</Link>
          {showAdmin && (
          <div className='popup-background' onClick={handleClosePopup}>
            <div className='popup-admin'>
              <Link className='home-link' to={"/login"}>Login</Link>
              <Link className='home-link' to={"/register"}>Register</Link>
            </div>
          </div>
        )}
          <Link className='home-link' to={"/empLogin"}>Employee</Link>
      </div>
    </div>
  )
}
