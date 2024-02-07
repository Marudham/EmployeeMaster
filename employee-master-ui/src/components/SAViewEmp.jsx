import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react';
import axios from 'axios';

export default function SAViewEmp() {

  const [employees, setEmployees] = useState([]);
  const [admins, setAdmins] = useState([]);
  const [ message, setMessage ] = useState('');

  useEffect( () => {
    fetchEmployee();
    fetchAdmin();
  }, []);

  async function fetchEmployee() {
    try {
      const response = await axios.get("http://localhost:8080/ems/controller/fetchAllEmployees");
      setEmployees(response.data.employeeList);
    } catch (error) {
      handleCommonError(error)
    }
  }

  async function fetchAdmin() {
    try {
      const response = await axios.get("http://localhost:8080/ems/controller/fetchAdmins");
      setAdmins(response.data.adminList);
    } catch (error) {
      handleCommonError(error)
    }
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
    <div className='sa-view-emp-container'>
      <h1 className='sa-view-emp-header'>Employee List</h1>
      <table className="sa-employee-table">
        <thead className="sa-employee-thead">
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Date_of_Birth</th>
            <th>Address</th>
            <th>Gender</th>
            <th>Education</th>
            <th>Percentage 10</th>
            <th>Percentage 12</th>
            <th>Percentage Degree</th>
            <th>Department</th>
            <th>Join_Date</th>
            <th>Position</th>
            <th>Salary</th>
            <th>Supervisor</th>
            <th>Project</th>
            <th>Status</th>
            <th>Added By</th>
          </tr>
        </thead>
        <tbody className="sa-employee-tbody">
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.firstName} {employee.secondName}</td>
              <td>{employee.email}</td>
              <td>{employee.phoneNo}</td>
              <td>{employee.dateOfBirth}</td>
              <td>{employee.address}</td>
              <td>{employee.gender}</td>
              <td>{employee.education}</td>
              <td>{employee.percentage10}</td>
              <td>{employee.percentage12}</td>
              <td>{employee.percentageDeg}</td>
              <td>{employee.department}</td>
              <td>{employee.joinDate}</td>
              <td>{employee.position}</td>
              <td>{employee.salary}</td>
              <td>{employee.supervisor}</td>
              <td>{employee.project}</td>
              <td>{employee.status}</td>
              <td>{admins && admins.find(admin => admin.id === employee.addedByAdminId)?.username}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
