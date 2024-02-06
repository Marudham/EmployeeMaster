import React, { useState } from 'react';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';
import { useSelector } from 'react-redux';
import { selectUser } from '../authSlice';
import { Link, useNavigate } from 'react-router-dom'; 

export default function AddEmp({ commonMessage, setCommonMessage }) {

  const user = useSelector(selectUser);
  const [ message, setMessage ] = useState("");
  const navigate = useNavigate();

 const formik = useFormik({
    initialValues: {
      firstName: '',
      secondName: '',
      email: '',
      phoneNo: '',
      dateOfBirth: '',
      address: '',
      gender: '',
      education: '',
      percentage10: '',
      percentage12: '',
      percentageDeg: '',
      department: '',
      joinDate: '',
      position: '',
      salary: '',
      supervisor: '',
      project: '',
      status: '',
    },
    validationSchema: Yup.object({
        firstName: Yup.string().required('First Name is required'),
        secondName: Yup.string().required('Second Name is required'),
        email: Yup.string().email('Invalid email address').required('Email is required'),
        phoneNo: Yup.string()
          .matches(/^[6789]\d{9}$/, 'Invalid phone number')
          .required('Phone Number is required'),
        dateOfBirth: Yup.date()
          .max(new Date(), 'Date of Birth cannot be in the future')
          .required('Date of Birth is required')
          .test('age-validation', 'Must be at least 18 years old', function (value) {
            const today = new Date();
            const birthDate = new Date(value);
            const age = today.getFullYear() - birthDate.getFullYear();
      
            return age >= 18;
          }),
        address: Yup.string().required('Address is required'),
        gender: Yup.string().required('Gender is required'),
        education: Yup.string().required('Education is required'),
        percentage10: Yup.number()
          .required('Percentage of 10th std is required')
          .max(100, 'Percentage cannot be more than 100'),
        percentage12: Yup.number()
          .required('Percentage of 12th std is required')
          .max(100, 'Percentage cannot be more than 100'),
        percentageDeg: Yup.number()
          .required('Percentage of College is required')
          .max(100, 'Percentage cannot be more than 100'),
        department: Yup.string().required('Department is required'),
        joinDate: Yup.date()
          .max(new Date(), 'Join Date cannot be in the future')
          .required('Join Date is required'),
        position: Yup.string().required('Position is required'),
        salary: Yup.string().required('Salary is required'),
        supervisor: Yup.string().required('Supervisor is required'),
        project: Yup.string().required('Project is required'),
        status: Yup.string().required('Status is required'),
      }),            
    onSubmit: async (values) => {
      try {
        const response = await axios.post("http://localhost:8080/ems/controller/addEmployee", values, {
          params: {
            id: user.id
          },
        });
        if(response.data.status === 'success'){
          setMessage("Employee Added successfully");
          setCommonMessage("Employee Added successfully")
          navigate("/adminHome/viewEmp")

        }
      } catch (error) {
        console.log(error);
        if (error.response && error.response.data && error.response.data.message) {
          setMessage(error.response.data.message);
        } else {
          setMessage("Unexpected Error has occurred!");
        }
      }
    },
  });

  return (
    <div className='eu-container'>
      <div className='eu-go-back-div'>
        <Link className='eu-go-back' to={'/adminHome/viewEmp'}>
          Go Back
        </Link>
      </div>
      <h2 className='eu-header'>Add Employee</h2>
      <form className='eu-form' onSubmit={formik.handleSubmit}>
        <div className='eu-form-group'>
          <label htmlFor='firstName'>First Name:</label>
          <input
            type='text'
            name='firstName'
            placeholder='Enter First Name'
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.firstName}
          />
          {formik.touched.firstName && formik.errors.firstName && (
            <div className='eu-error'>{formik.errors.firstName}</div>
          )}
        </div>

        <div className='eu-form-group'>
          <label htmlFor='secondName'>Second Name:</label>
          <input
            type='text'
            name='secondName'
            placeholder='Enter Second Name'
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.secondName}
          />
          {formik.touched.secondName && formik.errors.secondName && (
            <div className='eu-error'>{formik.errors.secondName}</div>
          )}
        </div>

        <div className='eu-form-group'>
          <label htmlFor='email'>Email:</label>
          <input
            type='email'
            name='email'
            placeholder='Enter Email'
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.email}
          />
          {formik.touched.email && formik.errors.email && <div className='eu-error'>{formik.errors.email}</div>}
        </div>

        <div className='eu-form-group'>
          <label htmlFor='phoneNo'>Phone Number:</label>
          <input
            type='tel'
            name='phoneNo'
            placeholder='Enter Phone Number'
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.phoneNo}
          />
          {formik.touched.phoneNo && formik.errors.phoneNo && (
            <div className='eu-error'>{formik.errors.phoneNo}</div>
          )}
        </div>

        <div className='eu-form-group'>
          <label htmlFor='dateOfBirth'>Date Of Birth:</label>
          <input
            type='date'
            name='dateOfBirth'
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.dateOfBirth}
          />
          {formik.touched.dateOfBirth && formik.errors.dateOfBirth && (
            <div className='eu-error'>{formik.errors.dateOfBirth}</div>
          )}
        </div>

        <div className='eu-form-group'>
          <label htmlFor='address'>Address:</label>
          <input
            type='text'
            name='address'
            placeholder='Enter Address'
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.address}
          />
          {formik.touched.address && formik.errors.address && (
            <div className='eu-error'>{formik.errors.address}</div>
          )}
        </div>
            <div className='eu-form-group'>
            <label htmlFor='gender'>Gender:</label>
            <select
                name='gender'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.gender}
            >
                <option value=''>Select Gender</option>
                <option value='Male'>Male</option>
                <option value='Female'>Female</option>
                <option value='Other'>Other</option>
            </select>
            {formik.touched.gender && formik.errors.gender && (
                <div className='eu-error'>{formik.errors.gender}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='education'>Education:</label>
            <input
                type='text'
                name='education'
                placeholder='Enter Education'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.education}
            />
            {formik.touched.education && formik.errors.education && (
                <div className='eu-error'>{formik.errors.education}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='percentage10'>Percentage of 10th std:</label>
            <input
                type='text'
                name='percentage10'
                placeholder='Enter Percentage of 10th std'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.percentage10}
            />
            {formik.touched.percentage10 && formik.errors.percentage10 && (
                <div className='eu-error'>{formik.errors.percentage10}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='percentage12'>Percentage of 12th std:</label>
            <input
                type='text'
                name='percentage12'
                placeholder='Enter Percentage of 12th std'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.percentage12}
            />
            {formik.touched.percentage12 && formik.errors.percentage12 && (
                <div className='eu-error'>{formik.errors.percentage12}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='percentageDeg'>Percentage of College:</label>
            <input
                type='text'
                name='percentageDeg'
                placeholder='Enter Percentage of College'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.percentageDeg}
            />
            {formik.touched.percentageDeg && formik.errors.percentageDeg && (
                <div className='eu-error'>{formik.errors.percentageDeg}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='department'>Department:</label>
            <input
                type='text'
                name='department'
                placeholder='Enter Department'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.department}
            />
            {formik.touched.department && formik.errors.department && (
                <div className='eu-error'>{formik.errors.department}</div>
            )}
            </div>
            <div className='eu-form-group'>
            <label htmlFor='joinDate'>Join Date:</label>
            <input
                type='date'
                name='joinDate'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.joinDate}
            />
            {formik.touched.joinDate && formik.errors.joinDate && (
                <div className='eu-error'>{formik.errors.joinDate}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='position'>Position:</label>
            <input
                type='text'
                name='position'
                placeholder='Enter Position'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.position}
            />
            {formik.touched.position && formik.errors.position && (
                <div className='eu-error'>{formik.errors.position}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='salary'>Salary:</label>
            <input
                type='text'
                name='salary'
                placeholder='Enter Salary'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.salary}
            />
            {formik.touched.salary && formik.errors.salary && (
                <div className='eu-error'>{formik.errors.salary}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='supervisor'>Supervisor:</label>
            <input
                type='text'
                name='supervisor'
                placeholder='Enter Supervisor'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.supervisor}
            />
            {formik.touched.supervisor && formik.errors.supervisor && (
                <div className='eu-error'>{formik.errors.supervisor}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='project'>Project:</label>
            <input
                type='text'
                name='project'
                placeholder='Enter Project'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.project}
            />
            {formik.touched.project && formik.errors.project && (
                <div className='eu-error'>{formik.errors.project}</div>
            )}
            </div>

            <div className='eu-form-group'>
            <label htmlFor='status'>Status:</label>
            <select
                name='status'
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.status}
            >
                <option value=''>Select Status</option>
                <option value='Active'>Active</option>
                <option value='InActive'>InActive</option>
            </select>
            {formik.touched.status && formik.errors.status && (
                <div className='eu-error'>{formik.errors.status}</div>
            )}
            </div>
            {message && (
            <p id="message">
              {message}
              <button className="no-message" onClick={() => setMessage('')}>
                X
              </button>
              </p>
            )}
            <div className='eu-form-group'>
            <button className='au-button' type='submit'>Add Employee</button>
            </div>
        </form>
    </div>
  )
};
