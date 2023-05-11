import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import vehicleService from '../Service/vehicle.service';

export default function Vehicle() {
  const [vehicleList, setVehicleList] = useState([]);

  useEffect(() => {
    vehicleService
      .getAllVehicle(localStorage.getItem('customer'))
      .then((res) => {
        console.log(res.data);
        setVehicleList(res.data);
      })
      .catch((error) => {
        console.log(error);
        alert('Error fetching vehicle list. Please try again later.');
      });
  }, []);

  const deleteVehicle = (vehicleNumber) => {
    vehicleService
      .deleteVehicle(vehicleNumber)
      .then((res) => {
        console.log(res.data);
        setVehicleList(vehicleList.filter((vehicle) => vehicle.vehicleNumber !== vehicleNumber));
      })
      .catch((error) => {
        console.log(error);
        alert('Error deleting vehicle. Please try again later.');
      });
  };

  return (
    <div className="container text-light" style={{ backgroundImage: "url('https://images.unsplash.com/photo-1544392329-1c7613a76751?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80')",
    backgroundSize: "cover", height:"90vh" }}>
      <h1 className="text-center">All Vehicles</h1>

      <table className="table  my-5 bg-dark  text-white-50" style={{border:"1px solid yellow"}}>
        <thead >
          <tr>
            <th scope="col">srNo</th>
            <th scope="col">Vehicle Number</th>
            <th scope="col">Vehicle Type</th>
            <th scope="col">Vehicle Model Name</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody className='text-white-50'>
          {vehicleList.map((vehicle, index) => (
            <tr key={vehicle.vehicleNumber}>
              <th scope="row">{index + 1}</th>
              <td>{vehicle.vehicleNumber}</td>
              <td>{vehicle.vehicleType}</td>
              <td>{vehicle.modelName}</td>
              <td>
                <Link to={`/editVehicle/${vehicle.vehicleNumber}`} className="btn btn-sm btn-warning mx-2">
                  Edit
                </Link>
                <button onClick={() => deleteVehicle(vehicle.vehicleNumber)} className="btn btn-sm btn-warning">
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
