import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function SAViewAdmin() {

  const [admins, setAdmins] = useState([]);
  const [ message, setMessage ] = useState('');

  useEffect( () => {
    fetchData();
  }, []);

  async function fetchData() {
    try {
      const response = await axios.get("http://localhost:8080/ems/controller/fetchAdmins");
      setAdmins(response.data.adminList);
    } catch (error) {
      handleCommonError(error)
    }
  }

  async function handleApprove(id) {
    try{
      const response = await axios.get(`http://localhost:8080/ems/controller/approve/${id}`);
      if(response.data.status === 'success'){
        setMessage("Admin Approve status has been updated successfully")
      }else{
        setMessage("Cannot update Admin approve status, Please try again")
      }
    }catch(error){
      handleCommonError(error)
    }finally{
      fetchData()
    }
  }

  async function handleDisapprove(id) {
      try{
        const response = await axios.get(`http://localhost:8080/ems/controller/disapprove/${id}`);
        if(response.data.status === 'success'){
          setMessage("Admin Approve status has been updated successfully")
        }else{
          setMessage("Cannot update Admin approve status, Please try again")
        }
      }catch(error){
        handleCommonError(error)
      }finally{
        fetchData();
      }
  }

  async function handleDelete(id){
    try {
      const response = await axios.get(`http://localhost:8080/ems/controller/deleteAdmin/${id}`);
      if(response.data.status === 'success'){
        setMessage("Admin Deleted successfully")
      }else{
        setMessage("Cannot Delete Admin, Please try again")
      }
    } catch (error) {
      handleCommonError(error);
    }finally{
      fetchData();
    }
  }

  function handleCommonError(error) {
    console.log(error.response.data);
    setMessage(error.response.data.message);
  }

  return (
    <div className="sa-view-container">
      <div className="sa-view-row">
        <h1>Admin List</h1>
      </div>
      { message && <p id="message">{message}<button className='no-message' onClick={() => setMessage('')}>X</button></p>}
      <table className="sa-view-table">
        <thead className="sa-view-thead">
          <tr>
            <th>Admin User Name</th>
            <th>Admin Email</th>
            <th>Admin Verified Status</th>
            <th>Admin Approve Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody className='sa-view-tbody'>
          {admins.map((admin) => (
            <tr key={admin.id}>
              <td>{admin.username}</td>
              <td>{admin.email}</td>
              <td>
                {admin.verified ? (
                  <div>Verified</div>
                ) : (
                  <div>
                    Not Verified
                  </div>
                )}
              </td>
              <td className='approveStatus'>
                {admin.admin ? (
                  <div>
                    Approved 
                    <button onClick={ () => handleDisapprove(admin.id)} className="btn btn-primary">
                       Disapprove
                    </button>
                  </div>
                ) : (
                  <div>
                    Not Approved 
                    <button onClick={ () => handleApprove(admin.id)} className="btn btn-primary">
                      Approve
                    </button>
                  </div>
                )}
              </td>
              <td>
                <button onClick={ () => handleDelete(admin.id)} className="btn btn-danger">
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
