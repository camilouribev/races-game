import React from "react";
import { Link } from "react-router-dom";
import "./HomePage.css";
import welcome from "../images/giphy.gif";
import arrow from "../images/share.png";

export default function HomePage() {
  return (
    <div className="homepage-container">
      <h1 className="home-title"> Car Race Game</h1>
      <div className="home-container">
        <div className="home-welcome-container">
          <img className="home-img" src={welcome} alt="welcome"></img>
        </div>
        <div className="home-instructions-container">
          <h2 className="instructions-title">Instrucciones</h2>
          <div>Aquí van las instrucciones</div>
          <button className="starGame-btn">
            <Link to="/addPlayers"> Ve al juego</Link>
            <img className="img-arrow" src={arrow} alt="play"></img>
          </button>
        </div>
      </div>
    </div>
  );
}
