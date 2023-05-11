import React from 'react'

export default function About() {
  return (
    <div style={{ backgroundImage: "url('https://images.unsplash.com/photo-1544392329-1c7613a76751?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80')",
    backgroundSize: "cover", height:"90vh" }}>
<div className="row">
            <div className="col-md-6 offset-md-3">
        <div className="card my-3 bg-dark text-white-50" style={{border:"1px solid yellow"} }>
            <div className="card-body">
                    <span >
                    <h1 className=' card-title text-center fs-1' >About Us</h1>
                    <hr />
                    

                        <p className="text-center">Our online parking slot booking platform offers a hassle-free way for users to reserve parking spaces in advance. With just a few clicks, users can easily select the date and time they need to park their vehicle, choose the location, and make a payment using a variety of payment options.

Our platform also offers a highly interactive interface, making it easy for users to navigate and find the information they need quickly. We provide detailed information about parking locations, including availability, prices, and other important details that users need to know before booking.

In addition to simplifying the parking booking process, our platform also offers features to help streamline the assessment cycle for parking lot owners. Our system provides detailed analytics and reporting tools to help parking lot owners track occupancy rates, revenue, and other important metrics. This helps parking lot owners make data-driven decisions to optimize their operations and improve their bottom line.

Overall, our platform offers a seamless and efficient way for users to reserve parking spaces online, while also providing valuable tools for parking lot owners to manage their operations more effectively. With our easy-to-use platform and comprehensive features, we are the ultimate destination for online parking slot booking.
                        </p>
                    
                    </span>
            </div>
            </div>
            </div>
        </div>    </div>
  )
}
