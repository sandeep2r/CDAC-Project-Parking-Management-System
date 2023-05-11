import React from 'react'
import './Slots.css';

export default function Slots(props) {
  
    const buttonCount = props.buttonCount;
    const buttons = [];
    const cards = [];
  
    for (let i = 1; i <= buttonCount; i++) {
      buttons.push(<button className='mx-3 my-3 btn btn-outline-success' id='slots'  key={i} >Slot {i}</button>);
      
      
   

    }

    return (
    <div>
    {buttons}  
    </div>
  )
}
