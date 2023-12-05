import React from 'react'
import Header from '../Header'
import {  useSelector } from 'react-redux/es/hooks/useSelector';
import './Profile.css';
const Profile = () => {
  return (
    <div>
        <Header/>
    <div className='container pt-5'>
      <div className='col-sm-4 m-auto text-start'>
        <h3 className='h3'>Edit Profile</h3>
        <p className='name mb-0'>Name:</p>
        <input  type='text' className='form p-2 mb-4 col-12'  placeholder='Name' name='name' defaultValue={useSelector((state)=>(state.login.name))} />
        <p className='name mb-0'>Email:</p>
        <input type="email" className="form p-2 col-12 mb-3" placeholder="name@example.com"name="email" defaultValue={useSelector((state)=>(state.login.email))} />
        <p className='picture'>Profile Picture:</p>
        <div className='bg-white col-4' style={{height:'120px'}}></div>
        <input type="file"  className="file btn-sm mb-3" accept="image/png, image/jpeg" name="pictrue"></input>
        <div className='buttons'>
            <button className='bt1 p-2 col-12 border-0 text-white'>Save</button><br></br><br></br>
            <button className='bt2 p-2 col-12 border-0 text-white'>Reset Password</button>
        </div>
      </div>
    </div>
   
    </div>
  )
}

export default Profile
