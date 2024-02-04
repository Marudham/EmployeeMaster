import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function SAViewAdmin() {
  const [admins, setAdmins] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/ems/controller/fetchAdmins");
        console.log(response.data); // Log the data from the response
        setAdmins(response.data.adminList);
      } catch (error) {
        console.error(error.response);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="sa-view-container">
      <div className="sa-view-row">
        <h1>Admin List</h1>
      </div>
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
                    <Link to={`/disapprove/${admin.id}`} className="btn btn-primary">
                       Disapprove
                    </Link>
                  </div>
                ) : (
                  <div>
                    Not Approved 
                    <Link to={`/approve/${admin.id}`} className="btn btn-primary">
                      Approve
                    </Link>
                  </div>
                )}
              </td>
              <td>
                <Link to={`/deleteAdmin/${admin.id}`} className="btn btn-danger">
                  Delete
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
