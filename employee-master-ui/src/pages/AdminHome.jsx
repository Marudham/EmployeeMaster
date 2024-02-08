import React, { useState } from 'react'
import { Routes, Route, Link } from 'react-router-dom';
import AdminNav from '../components/AdminNav';
import ViewEmployees from '../components/ViewEmployees';
import ViewAct from '../components/ViewAct';
import EmpReq from '../components/EmpReq';
import AddEmp from '../components/AddEmp';
import ViewEmployee from '../components/ViewEmployee';
import EmpUpdate from '../components/EmpUpdate';
import { useSelector } from 'react-redux';
import { selectUser } from '../authSlice';
import { useNavigate } from 'react-router-dom';

export default function AdminHome() {

  const [ commonMessage, setCommonMessage ] = useState("");
  const user = useSelector(selectUser);
  const navigate = useNavigate();

  if(user == null){
    navigate("/")
  }

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
          <Route path='/viewEmp' element={<ViewEmployees commonMessage={commonMessage} setCommonMessage={setCommonMessage} />} />
          <Route path='/viewEmp/:empId' element={<ViewEmployee commonMessage={commonMessage} setCommonMessage={setCommonMessage}/>} />
          <Route path='/viewAct' element={<ViewAct commonMessage={commonMessage} setCommonMessage={setCommonMessage} />} />
          <Route path='/empReq' element={<EmpReq commonMessage={commonMessage} setCommonMessage={setCommonMessage}/>} />
          <Route path='/addEmp' element={<AddEmp commonMessage={commonMessage} setCommonMessage={setCommonMessage}/>} />
          <Route path='/update/:empId' element={<EmpUpdate commonMessage={commonMessage} setCommonMessage={setCommonMessage} />} />
      </Routes>
    </div>
  )
}
