import React from 'react'
import { Link } from 'react-router-dom'

export default function Footer() {
  return (



<div className="container">
<div className="row">
<div className="card bg-dark">




<div className="card-body ">
<div className="row">
  <div className="col-sm-4">
      <div className="card-body text-white-50">
      <h6>About</h6>
                        <p className="text-justify">Your ultimate destination for online parking 
                        slot booking with an easy to use highly interactive platform and
                            simplify the assessment cycle.
                        </p>
      </div>
  </div>
  <div className="col-sm-4">
      <div className="card-body text-white-50">
      <h6>Address</h6>
                        <ul className="footer-links">
                            <li> PMS Pvt Ltd, </li>
                            <li> CDac Pune,Maharashtra -  415110</li>
                        </ul>
      </div>
  </div>
  <div className="col-sm-4">
      <div className="card-body text-white-50">
      <h6>Quick Links</h6>
                        <ul className="footer-links">
                            <li> <Link className='link-light text-white-50' to="/aboutus">About Us</Link></li>
                            <li> <Link className='link-light text-white-50' to="/contactus"> Contact Us </Link> </li>
                            {/* <li> <Link className='link-light text-white-50' to="/privacypolicy"> Privacy Policy </Link> </li> */}
                        </ul>
      </div>
  </div>
</div>
  </div>
  
</div>

</div>

</div>


      )
}
