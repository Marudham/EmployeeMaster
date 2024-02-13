import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom';

export default function PasteLink() {

  const [ link, setLink ] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
        e.preventDefault();
        const url = link.substring(6);
        navigate(`/${url}`);
  }

  return (
    <div className='login-container'>
      <form className='login-form' onSubmit={handleSubmit}>
          <h2>Employee Login</h2>
          <p id='message'></p>
          <input
              type="text"
              name="link"
              placeholder="Paste Here..."
              value={link}
              onChange={(e) => setLink(e.target.value)}
              required
          />
           <input
                type="submit"
                value='Login'
                className='register-submit'
            />
          <p className="new-user-text">
             For Preparing Link? <Link className='register-link' to={"/empLogin"}>Click Here.</Link>
          </p>
      </form>
   </div>

  )
}
