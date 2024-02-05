import React from 'react'
import { Routes, Route, Link } from 'react-router-dom';
import AdminNav from '../components/AdminNav';
import ViewEmployees from '../components/ViewEmployees';
import ViewAct from '../components/ViewAct';
import EmpReq from '../components/EmpReq';
import AddEmp from '../components/AddEmp';
import ViewEmployee from '../components/ViewEmployee';
import EmpDelete from '../components/EmpDelete';
import EmpUpdate from '../components/EmpUpdate';

export default function adminHome() {
  return (
    <div className="sa-container">
      <nav className='sa-nav'>
        <h1 className='sa-nav-header'>EmployeeMaster</h1>
        <ul className='sa-nav-ul'>
          <li className='sa-nav-li'>
            <Link className='sa-nav-link' to={'/adminHome'}>Admin Dashboard</Link>
          </li>
          <li>
            <Link className='sa-nav-link' to={'/'}>Log Out</Link>
          </li>
        </ul>
      </nav>
      <Routes>
          <Route path='/' element={<AdminNav />} />
          <Route path='/viewEmp' element={<ViewEmployees />} />
          <Route path='/viewEmp/:empId' element={<ViewEmployee />} />
          <Route path='/viewAct' element={<ViewAct />} />
          <Route path='/empReq' element={<EmpReq />} />
          <Route path='/addEmp' element={<AddEmp />} />
          <Route path='/update/:empId' element={<EmpUpdate />} />
          <Route path='/delete/:empId' element={<EmpDelete />} />
      </Routes>
    </div>
  )
}
