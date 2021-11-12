import TrackBoard from "../components/TrackBoard";
import { Link } from "react-router-dom";
import "./CarGame.css";
import dice from "../images/dice.png";
import play from "../images/play.png";

function CarGame() {
  return (
    <div className="Title-container">
      <h1 className="Title"> CAR GAME RACE </h1>
      <div className="Buttons-container">
        {/* Aquí pongo el boton mientras gameInProgress sea false */}
        
          <button>
          <Link to="/addPlayers"> Comenzar a jugar</Link>
            <img className="img-icon" src={play} alt="play"></img>
          </button>
        
        {/* Aquí pongo el boton mientras gameInProgress sea true  */}
        {/* <button>
          3. Lanzar dados
          <img className="img-icon" src={dice} alt="dice"></img>
        </button> */}
      </div>
      <div className="TrackBoard-container">
        <TrackBoard />
      </div>
    </div>
  );
}

export default CarGame;