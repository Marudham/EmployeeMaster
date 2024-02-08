import React ,{ useEffect, useState } from 'react'
import axios from 'axios'

export default function SAViewAct() {

  const [ activities, setActivities ] = useState([]);
  const [ message, setMessage ] = useState('');

  useEffect( () => {
    fetchActivity();
  }, [])

  async function fetchActivity(){
    try {
      const response = await axios.get("http://localhost:8080/ems/controller/fetchAllActivity");
      if(response.data.status === 'success'){
        setActivities(response.data.adminActivities.reverse())
      }
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

  function formatTimestamp(timestamp) {
    const date = new Date(timestamp);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const ampm = hours >= 12 ? 'pm' : 'am';
    const formattedTime = `${(hours % 12 || 12).toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')} ${ampm}`;
    return `${day}-${month}-${year} | ${formattedTime}`;
  }
  

  return (
    <div className='eview-act-container'>
      <h2 className='eview-act-header'>Activities</h2>
      {activities.length === 0 ? (
        <p className='eview-act-nomsg'>No activities found</p>
      ) : (
        <ul className='eview-act-ul'>
          {activities.map((activity, index) => (
            <li className='eview-act-li' key={index}>
              <p>Activity: {activity.activity}</p>
              <p>Change Made: {activity.changeMade ? activity.changeMade : 'Null'}</p>
              <p>Timestamp: {formatTimestamp(activity.timestamp)}</p>
              <p>Admin: {activity.admin.username}</p>
            </li>
          ))}
        </ul>
      )}
    </div>
  )
}
