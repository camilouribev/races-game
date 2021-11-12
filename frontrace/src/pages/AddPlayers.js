import React, { useState } from "react";
import { Link } from "react-router-dom";
import playerwoman from "../images/womanplayer.png";
import playerman from "../images/manplayer.png";
import play from "../images/play.png";
import track from "../images/track.png";
import "./AddPlayers.css";

export default function AddPlayers() {
  const [fields, setField] = useState([]);

  const [playersName, setPlayersName] = useState([]);
  const [trackLength, setTrackLength] = useState();
  const [currentPlayerName, setCurrentPlayerName] = useState("");

  const handleOnChange = (e) => {
    let auxField = [];
    for (let i = 1; i <= e.target.value; i++) {
      auxField.push(i);
      setField(auxField);
    }
  };

  const onSubmit = (e) => {
    e.preventDefault();

    const data = { ...playersName, ...trackLength };
    console.log("trackLength: " + trackLength);
    console.log("playersName: " + playersName);

    // dispatch(postQuestion(data));
  };

  const handlePlayersInput = (e) => {
    console.log(e.key);
    if (e.key === "Enter") {
      let array = [...playersName];
      array.push(currentPlayerName);
      setPlayersName(array);
      console.log(array);
      console.log("playersName:");
    }
  };
  console.log("playersName: " + playersName);

  return (
    <div className="addPlayers-container">
      <form onSubmit={onSubmit}>
        <h1>Ingrese la longitud de la pista</h1>
        <div className="select-img-container">
          <div className="select-img">
            <img className="img-icon" src={track} alt="track"></img>
          </div>
          <div className="select-container">
            <label>Escriba la longitud de la lista: </label>
            <input
              onChange={(e) => setTrackLength(e.target.value)}
              className="trackinput"
              placeholder="Kilometros"
            ></input>
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
              <option value="0">0</option>
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
                onChange={(e) => setCurrentPlayerName(e.target.value)}
                className="addplayers-input"
                // name={index}
                onKeyUp={handlePlayersInput}
                key={index}
                placeholder="Apodo jugador"
              ></input>
            );
          })}
        </div>
        <div className="addplayer-btn-container">
          <button className="starGame-btn" type="submit">
            Cargar información
          </button>
          <button className="starGame-btn" type="submit">
            <Link to="/game">Comenzar a jugar</Link>
            <img className="play-img" src={play} alt=""></img>
          </button>
        </div>
      </form>
    </div>
  );
}
