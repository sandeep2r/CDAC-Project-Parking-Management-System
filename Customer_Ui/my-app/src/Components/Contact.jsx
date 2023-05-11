import React from 'react'

export default function Contact() {
  return (
    <div style={{ backgroundImage: "url('https://images.unsplash.com/photo-1544392329-1c7613a76751?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80')",
    backgroundSize: "cover", height:"90vh" }}>
<div className="row">
            <div className="col-md-6 offset-md-3">
        <div className="card my-3 bg-dark text-white-50" style={{border:"1px solid yellow"} }>
            <div className="card-body">
                    <span className='text-center'>
                    <h1 className=' card-title text-center fs-1' >About Us</h1>
                    <hr />
                    
                    <h6 className='card-subtitle my-2'>Email ID:- cdacparking@gmail.com </h6>
                            <h6 className='card-subtitle my-2'>Mobile Number:- 9988776655</h6>
                            <h6 className='card-subtitle my-2'>Address:- CDac Pune</h6>
                    </span>
            </div>
            </div>
            </div>
        </div>    </div>
  )
}
