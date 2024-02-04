import React from 'react'
import {Link } from 'react-router-dom'

export default function SANav() {
  return (
    <div>
        <Link className='sanav' to={"/SAHome/viewAdmins"}>View Admins</Link>
        <Link className='sanav' to={"/SAHome/viewAct"}>View Activities</Link>
        <Link className='sanav' to={"/SAHome/viewEmp"}>View Employees</Link>
      
    </div>
  )
}
