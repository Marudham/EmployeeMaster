import React, { useState, useEffect } from 'react'
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom'
import { selectUser } from '../authSlice';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function ViewEmployees({ commonMessage, setCommonMessage }) {

  const [ employees, setEmployees ] = useState([]);
  const [ message, setMessage ] = useState('');
  const [ filterBasedOn, setFilterBasedOn ] = useState('')
  const [ filterValue, setFilterValue ] = useState('')
  const [ filter, setFilter ] = useState(false)
  const user = useSelector(selectUser);
  const navigate = useNavigate();

  useEffect( () => {
    fetchEmp();
  }, [] );

  async function fetchEmp(){
    try{
      if(user === null){
        navigate('/login');
        return;
      }
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

  const handleDelete = async (id) => {
    try {
      const response = await axios.get("http://localhost:8080/ems/controller/deleteEmployee", {
        params: {
          id: id
        },
      })
      if(response.data.status === 'success'){
        setMessage("Employee Deleted successfully")
      }else{
        setMessage("Problem in deleting the employeee");
      }
    } catch (error) {
      handleCommonError(error);
    }finally{
      fetchEmp();
    }
  }

  const handleSubmit = async (e) => {
    try {
      e.preventDefault();
      const response = await axios.get("http://localhost:8080/ems/controller/applyFilter", {
        params: {
          filterBasedOn: filterBasedOn,
          filterValue: filterValue,
          id: user.id
        }
      })
      if(response.data.status === 'success'){
        setEmployees(response.data.employeeList);
        if(response.data.employeeList.length != 0){
          setMessage("Filter applied successfully")
        }else{
          setMessage("No Result found based on the applied filter")
        }
      }else{
        setMessage("Problem in applying filter")
      }
    } catch (error) {
      handleCommonError(error);
    }
  }

  const handleCancelFilter = () => {
    setFilter(false) 
    fetchEmp();
  }

  function handleCommonError(error) {
    console.log(error);
    if (error.response && error.response.data && error.response.data.message) {
      setMessage(error.response.data.message);
    } else {
      setMessage("Unexpected Error has occurred!");
    }
  }  

  return (
    <div className="eview-container">
      <div className="eview-row">
        <h1 className='eview-header'>Employee List</h1>
      </div>
      <Link className='eview-btn eview-btn-n' to={'/adminHome/addEmp'}>Add Employee</Link>
      <button style={{marginLeft:'50px', fontWeight:'600'}} className='btn-ev btn-primary-f' onClick={ () => setFilter(true)}>Filter</button>
      {message && (
        <p id="message">
          {message}
          <button className="no-message" onClick={() => setMessage('')}>
            X
          </button>
        </p>
      )}
      <div className="filter-form">
        {filter && (
          <form className="mb-4" onSubmit={handleSubmit}>
            <div className="form-row">
              <div className="form-group col-md-3">
                <label htmlFor="filterDepartment">Filter Based on:</label>
                <select name="filterBasedOn" className="form-control" onChange={ (e) => setFilterBasedOn(e.target.value) }>
                  <option value="">-- select --</option>
                  <option value="firstName">First Name</option>
                  <option value="secondName">Second Name</option>
                  <option value="email">Email</option>
                  <option value="phoneNo">Phone No</option>
                  <option value="dateOfBirth">Date of Birth</option>
                  <option value="address">Address</option>
                  <option value="gender">Gender</option>
                  <option value="education">Education</option>
                  <option value="percentage10">Percentage 10</option>
                  <option value="percentage12">Percentage 12</option>
                  <option value="percentageDeg">Percentage Degree</option>
                  <option value="department">Department</option>
                  <option value="joinDate">Join Date</option>
                  <option value="position">Position</option>
                  <option value="salary">Salary</option>
                  <option value="supervisor">Supervisor</option>
                  <option value="project">Project</option>
                  <option value="status">Status</option>
                </select>
              </div>
              <div className="form-group col-md-3">
                <label htmlFor="filterPosition">Filter Value:</label>
                <input type="text" name="filterValue" placeholder="Enter Filter Value" onChange={ (e) => setFilterValue(e.target.value) } className="form-control" required/>
              </div>

              <div className="form-group col-md-2">
                <button type="submit" className="btn-ev btn-primary">Apply Filter</button>
                <button style={{marginLeft:'20px'}} className="btn-ev btn-danger" onClick={handleCancelFilter}>Cancel Filter</button>
              </div>
            </div>
          </form>
        )}
      </div>

      {commonMessage && (
        <p id="message">
          {commonMessage}
          <button className="no-message" onClick={() => setCommonMessage('')}>
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
        {employees.length === 0 ? (
        <p className='eview-act-nomsg'>No result found</p>
      ) :(
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
                <button className="eview-btn-delete" onClick={ () => handleDelete(employee.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      )}
      </table>
    </div>
  )
}
