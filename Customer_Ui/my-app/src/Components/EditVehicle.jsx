import React, { useEffect } from 'react'
import { useState } from 'react';
import { useParams } from 'react-router-dom';
import vehicleService from '../Service/vehicle.service';


export default function EditVehicle() {

    const [vehicle,setVehicle]=useState({
        vehicleNumber:"",
        vehicleType:"",
        // customer:"",

        customer:"",
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

    const data=useParams();
    // console.log(data.id);

    useEffect(()=>{
        console.log("customer from localStorage:", localStorage.getItem("customer"));

        vehicleService.getVehicleById(data.id).then((res)=>{
            console.log("cust",data.customer);
            console.log("customer from response",res.data.customer);
            setVehicle(res.data);
            console.log();
        }).catch((error)=>{
            console.log(error);
        })
    },[])


    const submit=(e)=>{
        e.preventDefault();
        setVehicle({
            vehicleNumber:"",
            vehicleType:"",
            customer:"",
            modelName:""
        });

        console.log("from state",vehicle.customer);
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
      <div className='login-container'>
            <div className="row">
                <div className="col-md-6 offset-md-3">
                    <div className="card my-5" >
                        <div className='card-header text-center fs-6'>
                            <h1>Add Vehicle</h1> 
                            {message && <p className='text-success'>{message}</p>}
                            {errorMessage && <p className='text-danger'>{errorMessage}</p>}
                        </div>
                        <div className="card-body ">
                            <form >
                                <div className='mb-3'>
                                    <label>enter Vehicle number</label>
                                    <input disabled
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
                                        <option disabled>choose</option>
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
                                        className='btn btn-primary mx-2' 
                                        onClick={(e)=>submit(e)}
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
