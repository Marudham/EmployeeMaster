import React from 'react'
import { Link } from 'react-router-dom'

export default function AdminNav() {
  return (
    <div>
        <Link className='sanav' to={"/adminHome/viewEmp"}>View Employees</Link>
        <Link className='sanav' to={"/adminHome/viewAct"}>View Activities</Link>
        <Link className='sanav' to={"/adminHome/empReq"}>Employee Requestes</Link>
    </div>
  )
}
