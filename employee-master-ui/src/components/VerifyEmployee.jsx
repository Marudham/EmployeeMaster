import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';

export default function VerifyEmployee() {

    const { token, id } = useParams();
    const [loading, setLoading] = useState(false);
    const [employee, setEmployee] = useState(null);
    const [admin, setAdmin] = useState(null);
    const [ requests, setRequests ] = useState([]);
    const [ isToken, setIsToken ] = useState(false);
    const [message, setMessage] = useState('');
    const [isVerified, setIsVerified] = useState(false);
    const [ field, setField ] = useState('')
    const [ value, setValue ] = useState('')
    const [ viewRequest, setViewRequest ] = useState(false);

    useEffect(() => {
        verifyEmployee(token, id);
    }, [token, id]);

    async function verifyEmployee(token, id) {
        setLoading(true);
        try {
            const response = await axios.get("http://localhost:8080/ems/controller/verifyEmployee", {
                params: {
                    token: token,
                    id: id
                },
            });
            if (response.data.status === 'success') {
                setEmployee(response.data.employee);
                setAdmin(response.data.admin);
                setMessage("Employee verified successfully");
                setIsVerified(true);
            } else {
                setMessage("Problem occurred while verifying Employee");
            }
        } catch (error) {
            console.log(error);
            if (error.response && error.response.data && error.response.data.message) {
                setMessage(error.response.data.message);
            } else {
                setMessage("Unexpected Error has occurred!");
            }
        } finally {
            fetchRequests();
            setLoading(false);
        }
    }

    async function fetchRequests(){
        if(employee?.id == null){
            return;
        }
        try {
            const response = await axios.get("http://localhost:8080/ems/controller/fetchRequestByEmp", {
            params: {
                id: employee?.id
            }
        })
        if(response.data.status === 'success'){
            setRequests(response.data.employeeRequests);
        }
        } catch (error) {
            console.log(error);
            if (error.response && error.response.data && error.response.data.message) {
                setMessage(error.response.data.message);
            } else {
                setMessage("Unexpected Error has occurred!");
            }
        }
    }

    function scrollToElement(id) {
        const element = document.getElementById(id);
        if (element) {
          element.scrollIntoView({ behavior: 'smooth' });
        }
      }

      const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/ems/controller/employeeRequest", { field, value },{
                params: {
                    id: employee.id
                }
            });
            if(response.data.status === 'success'){
                setMessage("Request Raised successfully")
            }else{
                setMessage("Problem occured while Raising Request")
            }
        } catch (error) {
            console.log(error);
          if (error.response && error.response.data && error.response.data.message) {
            setMessage(error.response.data.message);
          } else {
            setMessage("Unexpected Error has occurred!");
          }
        }finally{
            fetchRequests();
        }
      }

    return (
        <div className='ve-container'>
            <nav className='sa-nav'>
                <h1 className='sa-nav-header'>EmployeeMaster</h1>
                <ul className='sa-nav-ul-ve'>
                <li className='sa-nav-li'>
                    <Link className='sa-nav-link'  onClick={() => scrollToElement('employee-details')}>Employee Details</Link>
                    <Link style={{marginLeft:'60px'}} className='sa-nav-link'  onClick={() => scrollToElement('requests')}>View Requests</Link>
                </li>
                <li>
                    <Link className='sa-nav-link' to={'/'}>Log Out</Link>
                </li>
                </ul>
            </nav>
            {loading && <div className='ve-loading'></div>}
            {message && (
                <p id="message">
                    {message}
                    <button className="no-message" onClick={() => setMessage('')}>
                        X
                    </button>
                </p>
            )}
            {isVerified ? (
                <h4 className='ve-verified-status'>verified</h4>
            ) : (
                <h4 className='ve-verified-status' style={{color:'red'}}>cannot verify</h4>
            )}
            {employee && (
                <div id="employee-details" className="ve-employee-details">
                    <div className="ve-details-row">
                        <div className="ve-details-label">ID:</div>
                        <div className="ve-details-value">{employee.id}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">First Name:</div>
                        <div className="ve-details-value">{employee.firstName}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Last Name:</div>
                        <div className="ve-details-value">{employee.secondName}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Email:</div>
                        <div className="ve-details-value">{employee.email}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Phone No:</div>
                        <div className="ve-details-value">{employee.phoneNo}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Date of Birth:</div>
                        <div className="ve-details-value">{employee.dateOfBirth}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Address:</div>
                        <div className="ve-details-value">{employee.address}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Gender:</div>
                        <div className="ve-details-value">{employee.gender}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Education:</div>
                        <div className="ve-details-value">{employee.education}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Percentage 10th std:</div>
                        <div className="ve-details-value">{employee.percentage10}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Percentage 12th std:</div>
                        <div className="ve-details-value">{employee.percentage12}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Percentage Degree:</div>
                        <div className="ve-details-value">{employee.percentageDeg}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Join Date:</div>
                        <div className="ve-details-value">{employee.joinDate}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Department:</div>
                        <div className="ve-details-value">{employee.department}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Position:</div>
                        <div className="ve-details-value">{employee.position}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Salary:</div>
                        <div className="ve-details-value">{employee.salary}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Supervisor:</div>
                        <div className="ve-details-value">{employee.supervisor}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Project:</div>
                        <div className="ve-details-value">{employee.project}</div>
                    </div>
                    <div className="ve-details-row">
                        <div className="ve-details-label">Status:</div>
                        <div className="ve-details-value">{employee.status}</div>
                    </div>
                    { viewRequest ? (
                        <div id="requests" className='ve-tokens-list-container'>
                        <h2 className='ve-tokens-list-header'>Request List</h2>
                        <div className='ve-tokens-list-req'>
                            <div className='ve-tokens-list-titles'>
                                <ul>
                                    <li>SI.NO</li>
                                    <li>Field</li>
                                    <li>Value</li>
                                    <li>Is Approved</li>
                                    <li>Is Executed</li>
                                </ul>
                            </div>
                        { requests.length == 0 ? (
                            <p className='eview-act-nomsg'>No Result Found</p>
                        ) : (
                            <div className='ve-tokens-list-req'>
                                {requests.map( (request, index) => (
                                <ul key={index}>
                                    <li>{index + 1}</li>
                                    <li>{request.field}</li>
                                    <li>{request.value}</li>
                                    <li>{request.approved ? 'Approved' : 'Not Approved'}</li>
                                    <li>{request.executed ? 'Executed' : 'Not Executed'}</li>
                                </ul>
                            ))}
                            </div>
                        )}
                        </div>
                    </div>
                    ) : (
                    <button id='requests' onClick={()=> {setViewRequest(true); fetchRequests()}} style={{marginRight: '80px'}}className='ve-tokens-btn'>View Requests</button>
                    )}
                    <button onClick={()=> setIsToken(true)} className='ve-tokens-btn'>Raise Request</button>
                    <div className='filter-form'>
                    { isToken && (<div style={{padding:'10px', marginLeft:'50px'}}>
                                    <form className='ve-token-form' onSubmit={handleSubmit}>
                                    <div className='form-row'>
                                    <div className='form-group col-md-3'>
                                    <label>Select Field</label>
                                    <select className='form-control' name='field' value={field} onChange={(e)=> setField(e.target.value)}>
                                        <option className='ve-token-option' value="">-- select --</option>
                                        <option className='ve-token-option' value="firstName">First Name</option>
                                        <option className='ve-token-option' value="secondName">Second Name</option>
                                        <option className='ve-token-option' value="email">Email</option>
                                        <option className='ve-token-option' value="phoneNo">Phone No</option>
                                        <option className='ve-token-option' value="dateOfBirth">Date of Birth</option>
                                        <option className='ve-token-option' value="address">Address</option>
                                        <option className='ve-token-option' value="gender">Gender</option>
                                        <option className='ve-token-option' value="education">Education</option>
                                        <option className='ve-token-option' value="percentage10">Percentage 10</option>
                                        <option className='ve-token-option' value="percentage12">Percentage 12</option>
                                        <option className='ve-token-option' value="percentageDeg">Percentage Degree</option>
                                        <option className='ve-token-option' value="department">Department</option>
                                        <option className='ve-token-option' value="joinDate">Join Date</option>
                                        <option className='ve-token-option' value="position">Position</option>
                                        <option className='ve-token-option' value="salary">Salary</option>
                                        <option className='ve-token-option' value="supervisor">Supervisor</option>
                                        <option className='ve-token-option' value="project">Project</option>
                                        <option className='ve-token-option' value="status">Status</option>
                                    </select>
                                    </div>
                                    <div className='form-group col-md-3'>
                                    <label>Value :</label>
                                    <input type='text' name='value' value={value}  onChange={(e)=> setValue(e.target.value)} ></input>
                                    </div>
                                    <div className='form-group col-md-2'>
                                    <input type='submit' className='btn-ev btn-primary' value="Submit"></input>
                                    </div>
                                    </div>
                                    </form>
                                    <button className='btn-ev btn-danger' onClick={()=> setIsToken(false)}>Close</button>
</div>
                        )}
                    </div>
                </div>
            )}
            {admin && (
                <div className="ve-admin-info">
                    <h2>Admin Information</h2>
                    <div className="ve-info-row">
                        <div className="ve-info-label">Username:</div>
                        <div className="ve-info-value">{admin.username}</div>
                    </div>
                    <div className="ve-info-row">
                        <div className="ve-info-label">Email:</div>
                        <div className="ve-info-value">{admin.email}</div>
                    </div>
                </div>
            )}
        </div>
    );
}
