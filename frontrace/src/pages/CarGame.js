import TrackBoard from "../components/TrackBoard";
import "./CarGame.css";
import dice from "../images/dice.png";
import playerwoman from "../images/womanplayer.png";
import playerman from "../images/manplayer.png";
import play from "../images/play.png"

function CarGame() {
  return (
    <div className="Title-container">
      <h1 className="Title"> CAR GAME RACE </h1>
      <div className="Buttons-container">
         {/* Aquí pongo el boton mientras gameInProgress sea false */}
        <button> 
          2. Comenzar a jugar
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