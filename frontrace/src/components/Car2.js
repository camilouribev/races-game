import React from "react";
import caricon from "../images/crash2-2.png";
import "./Car.css"

export default function Car1() {
  return (
    <div className="car-container">
      <img className="car-icon" src={caricon} alt="caricon"></img>
    </div>
  );
}