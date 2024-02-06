import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'
import { useFormik } from 'formik';
import * as Yup from 'yup'

export default function Register() {

  const formik = useFormik({
    initialValues: {
      username: '',
      email: '',
      password: '',
    },
    validationSchema: Yup.object({
      username: Yup.string().required("User Name is Required"),
      email: Yup.string().email("Invalid email address").required("Email is Required"),
      password: Yup.string().required("Password is Required").min(8, "Password must be at least 8 characters long"),
    }),
    onSubmit: async (values) => {
      setLoading(true);
      try{
        const response = await axios.post("http://localhost:8080/ems/controller/register", values);
        if(response.data.status === 'success'){
          console.log("success");
          document.getElementById("message").innerHTML = response.data.message;
          setShowPopup(true);
        }
      }catch(error){
        console.log(error)
        if (error.response && error.response.data && error.response.data.message) {
          // setMessage(error.response.data.message);
          document.getElementById("message").innerHTML = error.response.data.message;
        } else {
          // setMessage("Unexpected Error has occurred!");
          document.getElementById("message").innerHTML = "Unexpected Error has occurred!";
        }
      }finally{
        setLoading(false);
      }
    }
  
  });

  const [ loading, setLoading ] = useState('');
  const [ showPopup, setShowPopup ] = useState('');

  const closePopup = () => {
    setShowPopup(false);
  }

  return (
    <div className='register-container'>
        <form className='register-form' onSubmit={formik.handleSubmit}>
            <h2>Registration</h2>
            <p className='message' id="message"></p>
            <input
                type="text"
                name="username"
                placeholder="Username"
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.username}
                required
            />
            {formik.touched.username && formik.errors.username && (
            <div className='eu-error'>{formik.errors.username}</div>
          )}
            <input
                type="email"
                name="email"
                placeholder="Email"
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.email}
                required
            />
             {formik.touched.email && formik.errors.email && (
            <div className='eu-error'>{formik.errors.email}</div>
          )}
            <input
                type="password"
                name="password"
                placeholder="Password"
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.password}
                required
            />
             {formik.touched.password && formik.errors.password && (
            <div className='eu-error'>{formik.errors.password}</div>
          )}
            <input
                type="submit"
                disabled={loading}
                value={loading ? 'Registering...' : 'Register'}
                className='register-submit'
            />
            {loading && <div className="spinner"></div>}
            <p className='login-link-text'>
                Already Registered? <Link className='login-link' to={"/login"}>Login</Link>
            </p>
        </form>
    </div>

  )
}