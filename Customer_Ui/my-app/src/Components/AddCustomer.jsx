import React, { useState } from 'react'
import customerService from '../Service/customer.service';

export default function AddCustomer() {
  
    const [customer,setCustomer]=useState({
      firstName:"",  
      lastName:"",  
      address:"",  
      email:"",
      mobileNumber:"",
      password:"",
    })

    const [errorMessage,setErrorMessage]=useState("");

    const handleChange=(e)=>{
        const value = e.target.value;
        setCustomer({...customer,[e.target.name]:value})
    }

    const [message,setMessage]=useState("");

    const [confirmPasswordField,setConfirmPasswordField]=useState("");

    const submitCustomer=(e)=>{
        e.preventDefault();
        setCustomer({
           firstName:"",  
           lastName:"",  
           address:"",  
           email:"",
           mobileNumber:"",
           password:"" ,
 
        });

        setConfirmPasswordField("");

        customerService.saveCustomer(customer).then((res)=>{
            setMessage("account created successfully");
        }).catch((error)=>{
            console.log(error);
        })
    }
    


    const confirmPassword=(e)=>{
        e.preventDefault();
        const value=e.target.value;
        setConfirmPasswordField(value);
        if(customer.password!==e.target.value){
            setErrorMessage("password and confirm password must be same");
        }else{
            setErrorMessage("");

        }
    }

    return (
    
    <div className='container'style={{ backgroundImage: "url('https://images.unsplash.com/photo-1544392329-1c7613a76751?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80')",
    backgroundSize: "cover", height:"100vh" }}>
        <div className="row">
            <div className="col-md-6 offset-md-3">
                <div className="card my-5 bg-dark text-white-50" style={{border:"1px solid yellow"}} >
                    <div className='card-header text-center fs-6'>
                       <h1>Sign Up</h1> 
                        {message && <p className='text-success'>{message}</p>}

                    </div>
                    <div className="card-body ">
                        <form onSubmit={(e)=>submitCustomer(e)}>
                            <div className='mb-3'>
                                <label>enter first name</label>
                                <input type="text" 
                                required
                                className='form-control' 
                                name="firstName"
                                value={customer.firstName}
                                onChange={(e) => handleChange(e)}
                                />
                            </div>

                            <div className='mb-3'>
                                <label>enter last name</label>
                                <input type="text" 
                                required
                                className='form-control' 
                                name="lastName"
                                value={customer.lastName}
                                onChange={(e) => handleChange(e)}

                                />
                            </div>
                            <div className='mb-3'>
                                <label>enter address </label>
                                <input type="text" 
                                required
                                className='form-control' 
                                name="address"
                                value={customer.address}
                                onChange={(e) => handleChange(e)}

                                />
                            </div>
                            <div className='mb-3'>
                                <label>enter email</label>
                                <input type="email" 
                                 pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" size="30" required
                                className='form-control' 
                                name="email"
                                value={customer.email}
                                onChange={(e) => handleChange(e)}

                                />
                            </div>

                            <div className='mb-3'>
                                <label>enter mobile number</label>
                                <input type="number" 
                                className='form-control' 
                                name="mobileNumber"
                                value={customer.mobileNumber}
                                onChange={(e) => handleChange(e)}

                                />
                            </div>

                            <div className='mb-3'>
                                <label>enter password</label>
                                <input type="password" 
                                className='form-control' 
                                name="password"
                                value={customer.password}
                                onChange={(e) => handleChange(e)}

                                />
                            </div>
                            {errorMessage && <p className='text-danger'>{errorMessage}</p>}
                            <div className='mb-3'>
                                <label>confirm password</label>
                                <input type="password" 
                                className='form-control' 
                                name="confirmPassword"
                                value={confirmPasswordField}
                                onChange={(e) => confirmPassword(e)}

                                />
                            </div>


                            <div className='text-center'>
                                <button className='btn btn-warning mx-2'  >Submit</button>
                                <input type="reset" 
                                className='btn btn-warning mx-2' 
                                value="reset"/>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
      
    </div>
  )
}
