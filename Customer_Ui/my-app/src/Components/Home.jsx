import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

const HomePage = (props) => {
  return (
    <div style={{ backgroundImage: "url('https://images.unsplash.com/photo-1544392329-1c7613a76751?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80')",
     backgroundSize: "cover", height:"90vh" }}>
    
    <div className="row text-light text-center align-items-center" style={{height: "100vh"}}>
        <div className="col-md-6 ">
        <h1>Welcome to the Parking System!</h1>
<p>Here's some information about what you can expect to find:</p>
<ul style={{ listStyleType: "none" }}>
  <li>Real-time availability of parking spaces</li>
  <li>Reservation system for parking spots</li>
  <li>Pricing information for different types of parking</li>
</ul>

        </div>
        <div className="col-md-6 ">
          <img src="https://images.pexels.com/photos/5214397/pexels-photo-5214397.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
           className="img-fluid images" alt="Placeholder" />
           <div className="row my-3">
            <div className="col-md-6 ">
            {props.text?(
              <Link className="text-dark btn btn-warning " style={{textDecoration:"none",border:"1px solid black"}} >
                 <h1>add vehicle</h1>
          </Link>
        ):(
          <></>
      )}
          
          
         
            </div>


            <div className="col-md-6 ">
            {props.text?(
            <Link className="text-dark btn btn-warning " style={{textDecoration:"none",border:"1px solid black"}} >
          
          <h1>show vehicle</h1>
         </Link>
         ):(
          <></>
      )}
            </div>
           </div>
                     {/* <video src="my-app/src/img/video.mp4" alt="dsfs" className="img-fluid" alt="Placeholder" controls autoPlay/> */}

        </div>
      </div>
    </div>
  );
};

export default HomePage;
