import React from 'react'
import { Link } from 'react-router-dom'

export default function Navbar(props) {

  // const [isLoggedIn, setIsLoggedIn] = useState(false);



  const changeStatus=()=>{
    localStorage.clear();
    props.changeState(false);
    // navigate("/login");
}

 

  return (
    <div>
      <nav className="navbar navbar-expand-lg text-warning bg-dark">
  <div className="container-fluid">
    {/* <img src='https://www.flaticon.com/free-icon/car_9851637' alt="Logo" width="30" height="24" className="d-inline-block align-text-top"/> */}
    <Link to="/" className="navbar-brand text-warning" ><h5>PMS</h5></Link>
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <div className="collapse navbar-collapse " id="navbarSupportedContent">
      <ul className="navbar-nav me-auto mb-2 mb-lg-0">
        <li className="nav-item">
          <Link to="/" className="nav-link active text-warning" aria-current="page" ><h5>Home</h5></Link>
        </li>

        <li className="nav-item">
        {props.text?(

        <Link to="/booking" className="nav-link active text-warning" aria-current="page" ><h5>Booking</h5></Link>
        ):(<></>)}

        </li>

        <li className="nav-item">

        <Link to="/aboutus" className="nav-link active text-warning" aria-current="page" ><h5>About Us</h5></Link>

        </li>

        <li className="nav-item">

        <Link to="/contactus" className="nav-link active text-warning" aria-current="page" ><h5>Contact Us</h5></Link>

        </li>

        <li className="nav-item">
        {props.text?(

        <Link to="/feedback" className="nav-link active text-warning" aria-current="page" ><h5>Feedback</h5></Link>
        ):(<></>)}

        </li>


        

        {/* <li className="nav-item">
        {props.text?(
        <Link to="/addVehicle" className="nav-link active" aria-current="page" ><h5>Add Vehicle</h5></Link>
        ):(<></>)}
        </li> */}

      </ul>

      <form className="d-flex" >
        
        {props.text?(
        <Link to="/profile"  className="btn btn-warning mx-2"  >profile</Link>
        ):(
          <></>
      )}

      {props.text?(
        <Link to="/" onClick={()=>changeStatus()} className="btn btn-warning mx-2"  >Sign out</Link>
        ):(
          <Link to="/login" className="btn btn-warning mx-2"  >Sign in</Link>

      )}
      {props.text?(
        <></>
        ):(
            <Link to="/addCustomer" className="btn btn-warning mx-2" >Sign Up</Link>

            )}
      </form>
      
    </div>
  </div>
</nav>
    </div>
  )
}
