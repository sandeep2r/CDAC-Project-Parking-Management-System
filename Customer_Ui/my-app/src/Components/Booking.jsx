import React, { useState,useEffect } from 'react'
import vehicleService from '../Service/vehicle.service';
import bookingService from '../Service/booking.service';
import { Link,useNavigate } from 'react-router-dom';


export default function Booking() {

    const [book,setBook]=useState({
        vehicleNumber:"",
        checkInTime:"",
        checkOutTime:""
    });

    const [vehicleList, setVehicleList] = useState([]);
    const [message,setMessage]=useState("");
    const [errorMessage,setErrorMessage]=useState("");
    
  useEffect(() => {
    vehicleService
      .getAllVehicle(localStorage.getItem('customer'))
      .then((res) => {
        console.log(res.data);
        setVehicleList(res.data);
        if (res.data.length > 0) {
            setBook({
                ...book,
                vehicleNumber: res.data[0].vehicleNumber // set to the vehicleNumber of the first item in the list
            });
        }
      })
      .catch((error) => {
        console.log(error);
        alert('Error fetching vehicle list. Please try again later.');
    });
}, []);


const handleChange=(e)=>{
    const value = e.target.value;
    setBook({...book,[e.target.name]:value})
}

const navigate= useNavigate();


const submit=(e)=>{
    e.preventDefault();
    setBook({
        vehicleNumber:"",
        checkInTime:"",
        checkOutTime:""
    });
    bookingService.saveBooking(book).then((res)=>{
        console.log(res.status);
        setMessage("Booking is done");
        setErrorMessage("");
        // navigate("/bill");
    }).catch((error)=>{
        setErrorMessage("something gone wrong");
        console.log(error);
    })
  }

  return (
    <div className='container' style={{ backgroundImage: "url('https://images.unsplash.com/photo-1544392329-1c7613a76751?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80')",
    backgroundSize: "cover", height:"90vh" }}>
      


            <div className='row'>
                    <div className='col-md-6 offset-md-3'>
                        <div className="card my-5 bg-dark text-white-50" style={{border:"1px solid yellow"}} >
                            <div className='card-body'>
                            <div className='card-header text-center fs-6'>
                            <h1>Booking</h1> 
                            {message && <p className='text-success'>{message}</p>}
                            {errorMessage && <p className='text-danger'>{errorMessage}</p>}
                        </div>
                            <form  >
                                <div className="mb-3">

                                <label>
                                    Vehicle Number:

                                </label>
                                <select required
                                        aria-label="Default select example"
                                        className='form-control form-select' 
                                        name="vehicleNumber"
                                        value={book.vehicleNumber} // set the value to vehicle.vehicleType
                                        onChange={(e) => handleChange(e)} 
                                        
                                    >

                                        <option  disabled>choose</option>
                                    {vehicleList.map((vehicle, index) => (
                                            
                                        <option key={vehicle.vehicleNumber} value={vehicle.vehicleNumber}>{vehicle.vehicleNumber}</option>
                                     ))}

                                    </select>
                                </div>
                                
                                <div className="mb-3">
                                <label>
                                    Check-In Time:
                                </label>
                                    <input className='form-control'  
                                    type="datetime-local" 
                                    name="checkInTime"
                                    value={book.checkInTime}
                                    onChange={(e) => handleChange(e)} 
                                    />
                                </div>
                                    
                                <label>
                                    Check-Out Time:
                                </label>
                                    <input className='form-control'  
                                    type="datetime-local" 
                                    name="checkOutTime"
                                    value={book.checkOutTime} 
                                    onChange={(e) => handleChange(e)} 
                                    />
                                <div className="mb-3"></div>
                                <div className="text-center">

                                <Link to="/bill" onClick={(e)=>submit(e)} className='btn btn-warning mx-2'  >Book</Link>
                                </div>
                            </form>
                            </div>
                        </div>

                    </div>
            </div>


         </div>
  )
}
