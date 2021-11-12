import React, { useState } from "react";
import { Link } from "react-router-dom";
import playerwoman from "../images/womanplayer.png";
import playerman from "../images/manplayer.png";
import play from "../images/play.png";
import track from "../images/track.png";
import "./AddPlayers.css";

export default function AddPlayers() {
  const [fields, setField] = useState([]);

  const handleOnChange = (e) => {
    let auxField = [];
    for (let i = 1; i <= e.target.value; i++) {
      auxField.push(i);
      setField(auxField);
    }
  };

  return (
    <div className="addPlayers-container">
      <form>
      <h1>Ingrese la longitud de la pista</h1>
        <div className="select-img-container">
          <div className="select-img">
            <img className="img-icon" src={track} alt="track"></img>
          </div>
          <div className="select-container">
            <label>Escriba la longitud de la lista: </label>
            <input className="track-input" placeholder="Kilometros"></input>
          </div>
        </div>
        <h1>Ingrese los jugadores</h1>
        <div className="select-img-container">
          <div className="select-img">
            <img className="img-icon" src={playerwoman} alt="play"></img>
            <img className="img-icon" src={playerman} alt="play"></img>
          </div>
          <div className="select-container">
            <label>Seleccione la cantidad de jugadores: </label>
            <select className="selectAddplayer" onChange={handleOnChange}>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
            </select>
          </div>
        </div>
        <div className="addplayers-input-container">
          {fields.map((index) => {
            return (
              <input
                className="addplayers-input"
                key={index}
                placeholder="Apodo jugador"
              ></input>
            );
          })}
        </div>
        <div className="addplayer-btn-container">
          <button className="starGame-btn">
            <Link to="/game">Comenzar a jugar</Link>
            <img className="play-img" src={play} alt=""></img>
          </button>
        </div>
      </form>
    </div>
  );
}
