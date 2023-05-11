import React from 'react'
import { useState } from 'react';
import vehicleService from '../Service/vehicle.service';

export default function AddVehicle() {

    const [vehicle,setVehicle]=useState({
        vehicleNumber:"",
        vehicleType:"",
        // customer:"",

        customer:localStorage.getItem("customer"),
        modelName:""
    });

    const [message,setMessage]=useState("");
    const [errorMessage,setErrorMessage]=useState("");

    const handleChange=(e)=>{
        const value = e.target.value;
        setVehicle({...vehicle,[e.target.name]:value})
        // vehicle.customer="1"
        // vehicle.vehicleType="Four_Wheeler_EV"
    }

    const submit=(e)=>{
        e.preventDefault();
        setVehicle({
            vehicleNumber:"",
            vehicleType:"",
            customer:localStorage.getItem("customer"),
            modelName:""
        });

        console.log(vehicle.vehicleType);
        vehicleService.saveVehicle(vehicle).then((res)=>{
            setMessage("vehicle added successfully");
            setErrorMessage("");
        }).catch((error)=>{
            setErrorMessage("choose the vehicle type");
            console.log(error);
        })

        // console.log(vehicle.vehicleType);
    }

    return (
        <div className='login-container' style={{ backgroundImage: "url('https://images.unsplash.com/photo-1544392329-1c7613a76751?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80')",
        backgroundSize: "cover", height:"90vh" }}>
            <div className="row">
                <div className="col-md-6 offset-md-3">
                    <div className="card my-5 bg-dark  text-white-50" style={{border:"1px solid yellow"}} >
                        <div className='card-header text-center fs-6'>
                            <h1>Add Vehicle</h1> 
                            {message && <p className='text-success'>{message}</p>}
                            {errorMessage && <p className='text-danger'>{errorMessage}</p>}
                        </div>
                        <div className="card-body ">
                            <form onSubmit={(e)=>submit(e)}>
                                <div className='mb-3'>
                                    <label>enter Vehicle number</label>
                                    <input 
                                        type="text" 
                                        required
                                        className='form-control' 
                                        name="vehicleNumber"
                                        value={vehicle.vehicleNumber}
                                        onChange={(e) => handleChange(e)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label>choose vehicle type</label>
                                    <select required
                                        aria-label="Default select example"
                                        className='form-control form-select' 
                                        name="vehicleType"
                                        value={vehicle.vehicleType} // set the value to vehicle.vehicleType
                                        onChange={(e) => handleChange(e)} 
                                        
                                    >
                                        {/* <option >choose option</option> */}
                                        <option >choose</option>
                                        <option value="TWO_WHEELER">Two Wheeler Normal</option>
                                        <option value="TWO_WHEELER_EV" >Two Wheeler EV</option>
                                        <option value="FOUR_WHEELER">Four Wheeler Normal</option>
                                        <option value="FOUR_WHEELER_EV">Four Wheeler EV</option>
                                    </select>
                                </div>
                                <div className='mb-3'>
                                    <label>enter model name</label>
                                    <input 
                                        type="text" 
                                        className='form-control' 
                                        name="modelName"
                                        value={vehicle.modelName}
                                        onChange={(e) => handleChange(e)}
                                    />
                                </div>
                                <div className='text-center'>
                                    <button 
                                        className='btn btn-warning mx-2' 
                                        // onClick={(e)=>submit(e)}
                                    >
                                        Add Vehicle
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
