import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import { selectUser } from '../authSlice'
import axios from 'axios'

export default function EmpReq() {

  const user = useSelector(selectUser);
  const [ requests, setRequests ] = useState([])
  const [ message, setMessage ] = useState('')

  useEffect( () => {
    fetchRequests();
  },[])

  async function fetchRequests(){
    try {
      const response = await axios.get("http://localhost:8080/ems/controller/fetchRequests",{params: {id: user.id}});
      if(response.data.status === 'success'){
        setRequests(response.data.employeeRequests.reverse())
        // setMessage("")
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

  async function handleApprove(id) {
    try {
      const response = await axios.get("http://localhost:8080/ems/controller/approveRequest", {
        params: {
          id: id
        }
      })
      if(response.data.status === 'success'){
        setMessage("Approve status has been updated")
      }else{
        setMessage("Problem occured while updating Approve status")
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

  async function handleExecute(id) {
    try {
      const response = await axios.get("http://localhost:8080/ems/controller/handleExecute", {
        params: {
          id: id
        }
      })
      if(response.data.status === 'success'){
        setMessage("Employee Request executed successfully")
      }else{
        setMessage("Problem occured while updating Approve status")
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
    <div className='emp-req-container'>
      <h1 className='emp-req-header'>Employee Requests</h1>
      <p id="message">
          {message}
          <button className="no-message" onClick={() => setMessage('')}>
            X
          </button>
        </p>
      <div className='emp-req-list-container'>
                        <h2 className='emp-req--list-header'>Request List</h2>
                        <div className='emp-req-list-req'>
                            <div className='emp-req-list-titles'>
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
                            <div className='emp-req-list-req'>
                                {requests.map( (request, index) => (
                                <ul key={index}>
                                    <li>{index + 1}</li>
                                    <li>{request.field}</li>
                                    <li>{request.value}</li>
                                    <li>{request.approved ? 'Approved' : (
                                      <button className='emp-req-approve-btn' onClick={() => handleApprove(request.id)} >Approve</button>
                                    )}</li>
                                    <li>{request.executed ? 'Executed' : (
                                      <button className='emp-req-execute-btn' onClick={() => handleExecute(request.id)}>Execute</button>
                                    )}</li>
                                </ul>
                            ))}
                            </div>
                        )}
                        </div>
                    </div>
    </div>
  )
}
