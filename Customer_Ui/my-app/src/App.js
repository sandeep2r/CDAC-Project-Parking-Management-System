import './App.css';
import Navbar from './Components/Navbar';
import AddCustomer from './Components/AddCustomer';
import {Routes,Route} from "react-router-dom";
import Login from './Components/Login';
import Home from './Components/Home';
import AddVehicle from './Components/AddVehicle';
import { useState } from 'react';
import Profile from './Components/Profile';
import Footer from './Components/Footer';
import Vehicle from './Components/Vehicle';
import EditVehicle from './Components/EditVehicle';
import Booking from './Components/Booking';
import Bill from './Components/Booking';
import About from './Components/About';
import Contact from './Components/Contact';
import Feedback from './Components/Feedback';

function App() {


  const [status,setStatus]=useState(false);

  const handleStateChange = (newValue) => {
    setStatus(newValue);
  }
  
  return (
    <div className='container' >
      <Navbar text={status} changeState={handleStateChange}/>
      <Routes>
        <Route path='/addCustomer' element={<AddCustomer/>}></Route>
        <Route path='/login' element={<Login   changeState={handleStateChange}/>}></Route>
        <Route path='/' element={<Home text={status}/>}></Route>
        <Route path='/addVehicle' element={<AddVehicle/>}></Route>
        <Route path='/getVehicle' element={<Vehicle/>}></Route>
        <Route path='/editVehicle/:id' element={<EditVehicle/>}></Route>
        <Route path='/profile' element={<Profile/>}></Route>
        <Route path='/booking' element={<Booking/>}></Route>
        <Route path='/bill' element={<Bill/>}></Route>
        <Route path='/aboutus' element={<About/>}></Route>
        <Route path='/contactus' element={<Contact/>}></Route>
        <Route path='/feedback' element={<Feedback/>}></Route>
        

      </Routes>
      <Footer className="footer"/>
    </div>
  );
}

export default App;
