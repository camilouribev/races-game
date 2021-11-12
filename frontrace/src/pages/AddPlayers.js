import React, { useState } from "react";
import { Link } from "react-router-dom";
import playerwoman from "../images/womanplayer.png";
import playerman from "../images/manplayer.png";
import play from "../images/play.png";
import track from "../images/track.png";
import add from "../images/add.png";
import "./AddPlayers.css";

export default function AddPlayers() {
  const [fields, setField] = useState([]);

  const [playersName, setPlayersName] = useState([]);
  const [trackLength, setTrackLength] = useState();
  const [currentPlayerName, setCurrentPlayerName] = useState("");
  const [rows, setRows] = useState([]);


  const onSubmit = (e) => {
    e.preventDefault();

    const data = { ...playersName, ...trackLength };
    console.log("trackLength: " + trackLength);
    console.log("playersName: " + playersName);

    // dispatch(postQuestion(data));
  };

  const handlePlayersInput = (e) => {
      let array = [...playersName];
      array.push(currentPlayerName);
      setPlayersName(array);
      console.log(array);
      console.log("playersName:");
      setRows([...playersName])
  };
  console.log("playersName: " + playersName);
  console.log("");

  return (
    <div className="addPlayers-container">
      <form onSubmit={onSubmit}>
        <h1>Ingrese la longitud de la pista</h1>
        <div className="select-img-container">
          <div className="select-img">
            <img className="img-icon" src={track} alt="track"></img>
          </div>
          <div className="select-container">
            <label>Escriba la longitud de la pista: </label>
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
          <div className="addplayers-input-container">
            <label>Agrege el nombre de los jugadores: </label>
            <div className="addplayers-input-contain">
              <input
                onChange={(e) => setCurrentPlayerName(e.target.value)}
                className="addplayers-input"
                placeholder="Apodo jugador"
              ></input>
              <button
                className="btn-add"
                onClick={handlePlayersInput}
              >
                <img className="btn-img" src={add} alt="add"></img>
              </button>
            </div>
            {rows.map((index) => (
              <div>
                <input
                  onChange={(e) => setCurrentPlayerName(e.target.value)}
                  className="addplayers-input"
                  placeholder="Apodo jugador"
                  key={index}
                ></input>
                <button
                  className="btn-add"
                  onClick={handlePlayersInput}
                >
                  <img className="btn-img" src={add} alt="add"></img>
                </button>
              </div>
            ))}
          </div>
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
