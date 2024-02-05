import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';

export default function ViewEmployee() {
  const { empId } = useParams();
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    const fetchEmployee = async () => {
      try {
        const response = await axios.get("http://localhost:8080/ems/controller/employeeDetails",
                {
                    params: {
                        id: empId
                },
                });
        setEmployee(response.data.employee);
      } catch (error) {
        console.log(error.response);
      }
    };

    fetchEmployee(); 
  }, []);

  return (
    <div className="employee-details">
      <h2 className="employee-details-header">Employee Details</h2>
      {employee && (
        <div className="employee-details-form">
          <label>First Name:</label> <span>{employee.firstName}</span>
          <label>Last Name:</label> <span>{employee.secondName}</span>
          <label>Email:</label> <span>{employee.email}</span>
          <label>Phone Number:</label> <span>{employee.phoneNo}</span>
          <label>Date Of Birth:</label> <span>{employee.dateOfBirth}</span>
          <label>Address:</label> <span>{employee.address}</span>
          <label>Gender:</label> <span>{employee.gender}</span>
          <label>Education:</label> <span>{employee.education}</span>
          <label>Percentage 10th std:</label> <span>{employee.percentage10}</span>
          <label>Percentage 12th std:</label> <span>{employee.percentage12}</span>
          <label>Percentage Degree:</label> <span>{employee.percentageDeg}</span>
          <label>Join Date:</label> <span>{employee.joinDate}</span>
          <label>Department:</label> <span>{employee.department}</span>
          <label>Position:</label> <span>{employee.position}</span>
          <label>Salary:</label> <span>{employee.salary}</span>
          <label>Supervisor:</label> <span>{employee.supervisor}</span>
          <label>Project:</label> <span>{employee.project}</span>
          <label>Status:</label> <span>{employee.status}</span>
        </div>
      )}
      <div className="employee-details-button-container">
        <Link to={`/adminHome/update/${employee?.id}`} className="update">Update</Link>
        <Link to={`/adminHome/delete/${employee?.id}`} className="delete">Delete</Link>
        <Link to={'/adminHome/viewEmp'} className="back">Go Back</Link>
      </div>
    </div>
  );
}
