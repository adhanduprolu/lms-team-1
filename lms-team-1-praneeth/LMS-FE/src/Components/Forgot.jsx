import React from 'react'
import { Link } from 'react-router-dom'
const Forgot = () => {
  return (
    <div>
      <div className='bg-dark d-flex justify-content-center align-items-center' style={{height:'100vh'}}>
        <div className='bg-white rounded-3 col-sm-3 py-3 px-4'>
          <img src="https://digital-lync.konalms.com/assets/logo.ab024049.png" className='py-4' alt="logo" width={"200px"}/>
          <h4 className='fw-normal pb-2'>Forgot Your Password?</h4>
          <p style={{fontSize:"13px"}}>Enter your email address and we will send you instructions to reset your password</p>
          <div className='px-2 py-3 d-flex flex-column gap-3'>
            <input type="email" placeholder='Email address' className='col-12 py-2 px-3 rounded-1'/>
            <Link to="/" className='my-2'>
                        <button className="col-12 py-2 px-3 btn btn-primary">
                            Continue
                        </button></Link>
            <p className='text-center' style={{fontSize:"14px", fontWeight:"500"}}><Link to="/login" className='text-decoration-none'>Back to Digital Lync LMS</Link></p>
          </div>
        </div>
    </div>
    </div>
  )
}

export default Forgot
