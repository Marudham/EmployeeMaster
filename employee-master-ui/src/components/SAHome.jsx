import React from 'react'
import { Link, Routes, Route } from 'react-router-dom';
import SAViewAdmin from './SAViewAdmin'
import SAViewAct from './SAViewAct'
import SAViewEmp from './SAViewEmp'
import SANav from './SANav'

export default function SAHome() {

  return (
    <div className="sa-container">
    <nav className='sa-nav'>
      <h1 className='sa-nav-header'>SUPER ADMIN</h1>
      <ul className='sa-nav-ul'>
        <li className='sa-nav-li'>
          <Link className='sa-nav-link' to={'/SAHome'}>Super Admin Dashboard</Link>
        </li>
        <li>
          <Link className='sa-nav-link' to={'/logout'}>Log Out</Link>
        </li>
      </ul>
    </nav>
    <Routes>
        <Route path='/' element={<SANav />} />
        <Route path='/viewAdmins' element={<SAViewAdmin />} />
        <Route path='/viewAct' element={<SAViewAct />} />
        <Route path='/viewEmp' element={<SAViewEmp />} />
    </Routes>
  </div>
  )
}
