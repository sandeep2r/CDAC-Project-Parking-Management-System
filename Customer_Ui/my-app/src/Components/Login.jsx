import React, { useState } from 'react'
import customerService from '../Service/customer.service';
import {useNavigate} from "react-router-dom";



export default function Login(props) {

    

    const [authRequest,setAuthRequest]=useState({
        loginId:"",
        password:""
    });

    const [errorMessage,setErrorMessage]=useState("");


    const handleChange=(e)=>{
        const value = e.target.value;
        setAuthRequest({...authRequest,[e.target.name]:value})
    }

    
    const navigate= useNavigate();
    const submitCredential=(e)=>{
        e.preventDefault();

        setAuthRequest({
            loginId:"",
            password:""
        });
        customerService.login(authRequest).then((res)=>{
            if(res.status===200){
                localStorage.setItem("customer",res.data.id);
                console.log("loged in");
                navigate("/");
                setErrorMessage("");
                props.changeState(true);
            }
            
        }).catch((error)=>{
            setErrorMessage("Incorrect ID or Password");
        })

    }


  return (
    
      <div className='login-container ' style={{ backgroundImage: "url('https://images.unsplash.com/photo-1544392329-1c7613a76751?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80')",
      backgroundSize: "cover", height:"90vh" }}>
        <div className="row ">
            <div className="col-md-6 offset-md-3">
                <div className="card my-5 bg-dark text-white-50" style={{border:"1px solid yellow"}} >
                    <div className='card-header text-center fs-6 '>
                      <h1 >Login</h1> 
                      {errorMessage && <p className='text-danger'>{errorMessage}</p>}

                    </div>
                    <div className="card-body  ">
                        <form >
                            
                            <div className='mb-3'>
                                <label>enter login Id</label>
                                <input type="email" 
                                 pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" size="30" required
                                className='form-control' 
                                name="loginId"
                                value={authRequest.loginId}
                                onChange={(e) => handleChange(e)}

                                />
                            </div>

                            

                            <div className='mb-3'>
                                <label>enter password</label>
                                <input type="password" 
                                className='form-control' 
                                name="password"
                                value={authRequest.password}
                                onChange={(e) => handleChange(e)}

                                />
                            </div>
                           


                            <div className='text-center'>
                                <button className='btn btn-warning mx-2'  >Admin Login</button>
                                <button className='btn btn-warning mx-2'  >Agent Login</button>
                                <button className='btn btn-warning mx-2' onClick={(e)=>submitCredential(e)} >Customer Login</button>
                                
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
     
    </div>
  )
}
