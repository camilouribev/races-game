import React from "react";
import firstplace from "../images/firstplace.png";
import secondplace from "../images/secondplace.png";
import thirdplace from "../images/thirdplace.png";
import "./Podium.css"

export default function Podium() {
  return (
    <div className="Podium-container">
      <div className="Podium-card">
        <div className="firstplace-container">
          <img
            className="firstplace-img"
            src={firstplace}
            alt="firstplace"
          ></img>
          <h2 className="placename-title">Primer jugador</h2>
        </div>
        <div className="secondplace-container">
          <img
            className="place-img"
            src={secondplace}
            alt="secondplace"
          ></img>
          <h2 className="placename-title">Segundo jugador</h2>
        </div>
        <div className="thirdplace-container">
          <img className="place-img" src={thirdplace} alt="thirdplace"></img>
          <h2 className="placename-title">Tercer jugador</h2>
        </div>
      </div>
    </div>
  );
}
