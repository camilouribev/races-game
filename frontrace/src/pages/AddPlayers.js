import React, { useState, useContext } from "react";
import { Link } from "react-router-dom";
import { Store } from "../provider/Provider";

import playerwoman from "../images/womanplayer.png";
import playerman from "../images/manplayer.png";
import play from "../images/play.png";
import track from "../images/track.png";
import add from "../images/add.png";
import "./AddPlayers.css";
import { types } from "../reducers/reducer";

export default function AddPlayers() {
  const [store, dispatch] = useContext(Store);
  const { players, tracklength } = store;
  const [playersName, setPlayersName] = useState([{ id: "", name: "" }]);
  const [trackLength, setTrackLength] = useState();
  const [currentPlayerName, setCurrentPlayerName] = useState();
  const { v4: uuidv4 } = require("uuid");
  const onSubmit = (e) => {
    e.preventDefault();

    const data = { players: playersName, trackLength: trackLength };

    // dispatch(postQuestion(data));
  };

  const handlePlayersInput = () => {
    let array = [...playersName];
    array.push({ id: uuidv4(), name: currentPlayerName });

    setCurrentPlayerName("");
    setPlayersName(array);
  };
  console.log(playersName);

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
                required
                type="text"
                value={currentPlayerName}
                onChange={(e) => {
                  setCurrentPlayerName(e.target.value);
                }}
                className="addplayers-input"
                placeholder="Apodo jugador"
              ></input>
              <button className="btn-add" onClick={handlePlayersInput}>
                <img className="btn-img" src={add} alt="add"></img>
              </button>
            </div>
          </div>
        </div>
        <div className="addplayer-btn-container">
          <button
            className="starGame-btn"
            type="submit"
            onClick={() => dispatch({ type: types.addGameId })}
          >
            <Link to="/game">Comenzar a jugar</Link>
            <img className="play-img" src={play} alt=""></img>
          </button>
        </div>
      </form>
    </div>
  );
}
