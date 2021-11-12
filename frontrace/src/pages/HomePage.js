import React from "react";
import "./HomePage.css";
import welcome from "../images/giphy.gif";

export default function HomePage() {
  return (
    <div className="homepage-container">
      <h1 className="home-title"> Car Race Game</h1>
      <div className="home-container">
        <div className="home-welcome-container">
          <img className="home-img" src={welcome} alt="welcome"></img>
        </div>
        <div className="home-instructions-container">
          <h2 className="home-title">Instrucciones</h2>
          <div>Aquí van las instrucciones</div>
        </div>
      </div>
    </div>
  );
}
