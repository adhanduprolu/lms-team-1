import React from 'react'
import Header from '../Header'

const CourseDashboard = () => {
  return (
    <div>
      <Header/>
      <h1 className='mt-5'>Course name</h1>
      <div className='container-fluid d-flex'>
        <div className='col-4'>
          <div>
            <h5 className='px-2 py-3 m-0 border text-start'><span className='border rounded-circle px-2 bg-primary'>1</span> Topic Name</h5>
          </div>

          <div>
            <h5 className='p-2 py-3 m-0 border text-start'><span className='border rounded-circle px-2 bg-primary'>2</span> Topic Name</h5>
          </div>
          <div>
            <h5 className='p-2 py-3 m-0 border text-start'><span className='border rounded-circle px-2 bg-primary'>3</span> Topic Name</h5>
          </div>
          <div>
            <h5 className='p-2 py-3 m-0 border text-start'><span className='border rounded-circle px-2 bg-primary' >4</span> Topic Name</h5>
          </div>
          <div><h5 className='p-2 py-3 m-0 border text-start'><span className='border rounded-circle px-2 bg-primary'>5</span> Topic Name</h5></div>
        </div>
        <div className='col-8 d-flex align-items-center justify-content-center' style={{height:'75vh'}}>
          <h4>Select the topic for display</h4>
        </div>
      </div>


    </div>
  )
}

// this is git

export default CourseDashboard
