import React, { useState, useEffect } from 'react'
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom'
import { selectUser } from '../authSlice';
import axios from 'axios';

export default function ViewEmployees() {

  const [ employees, setEmployees ] = useState([]);
  const [ message, setMessage ] = useState('');
  const user = useSelector(selectUser);
  
  useEffect( () => {
    fetchEmp();
  }, [] );

  async function fetchEmp(){
    try{
      const response = await axios.get("http://localhost:8080/ems/controller/fetchEmployees", {params: {id: user.id},});
      if(response.data.status === 'success'){
        setEmployees(response.data.employeeList);
      }else{
        setMessage("Cannot retrieve employee details...")
      }
    }catch(error){
      handleCommonError(error);
    }
  }

  function handleCommonError(error){
    console.log(error);
    setMessage(error.response.data.message);
  }

  return (
    <div className="eview-container">
      <div className="eview-row">
        <h1 className='eview-header'>Employee List</h1>
      </div>
      <Link className='eview-btn eview-btn-n' to={'/adminHome/addEmp'}>Add Employee</Link>
      <Link style={{marginLeft:'40px'}} className='eview-btn eview-btn-n' to={'/adminHome/empFilter'}>Filter</Link>
      {message && (
        <p id="message">
          {message}
          <button className="no-message" onClick={() => setMessage('')}>
            X
          </button>
        </p>
      )}
      <table className="eview-table">
        <thead className="eview-thead">
          <tr className='eview-tr'>
            <th>Employee First Name</th>
            <th>Employee Second Name</th>
            <th>Employee Email</th>
            <th>All Details</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody className="eview-tbody">
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.firstName}</td>
              <td>{employee.secondName}</td>
              <td>{employee.email}</td>
              <td>
                <Link className="eview-link" to={`/adminHome/viewEmp/${employee.id}`}>
                  View All Details
                </Link>
              </td>
              <td>
                <Link className="eview-btn-update" to={`/adminHome/update/${employee.id}`}>
                  Update
                </Link>
                <Link className="eview-btn-delete" to={`/adminHome/delete/${employee.id}`}>
                  Delete
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
