import React,{useState,useEffect} from 'react'
import { Link } from 'react-router-dom';
import customerService from '../Service/customer.service';

export default function Profile() {

    const [customer,setCustomer]=useState({});

    useEffect(()=>{
        customerService
        .getCustomerById(localStorage.getItem("customer"))
        .then((res)=>{
            console.log(res.data);
            setCustomer(res.data);
        }).catch((error)=>{
            console.log(error);
        });
    },[]);

  return (
    <div className='container 'style={{ backgroundImage: "url('https://images.unsplash.com/photo-1544392329-1c7613a76751?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80')",
    backgroundSize: "cover", height:"90vh" }}>
        <div className="row">
            <div className="col-md-6 offset-md-3">
        <div className="card my-3 bg-dark text-white-50" style={{border:"1px solid yellow"} }>
            <div className="card-body">
                    <span >
                    <h1 className=' card-title text-center fs-1' >Profile</h1>
                    <hr />
                        {/* <label>First Name: </label> */}
                            <h2 className='card-subtitle my-2'>Hi {customer.firstName} {customer.lastName}</h2>
                            <h6 className='card-subtitle my-2'>Email ID:- {customer.email} </h6>
                            <h6 className='card-subtitle my-2'>Mobile Number:- {customer.mobileNumber}</h6>
                            <h6 className='card-subtitle my-2'>Address:- {customer.address}</h6>
                            <div className='text-center'>

                            <Link to="/getVehicle" className='btn btn-warning my-3 mx-3'>Show Vehicles </Link>
                            <Link to="/addVehicle" className='btn btn-warning my-3 mx-3'>Add Vehicles </Link>
                            </div>
                    </span>
            </div>
            </div>
            </div>
        </div>
    </div>

    )
}
