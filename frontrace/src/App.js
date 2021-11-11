import TrackBoard from "./components/TrackBoard";
import "./App.css";
import dice from "./images/dice.png";
import playerwoman from "./images/womanplayer.png";
import playerman from "./images/womanplayer.png";
import play from "./images/play.png"

function App() {
  return (
    <div className="Title-container">
      <h1 className="Title"> CAR GAME RACE </h1>
      <div className="Buttons-container">
        <button>
          Agregar jugadores
          <img className="img-player" src={playerwoman} alt="playerwoman"></img>
          <img className="img-player" src={playerman} alt="playerman"></img>
        </button>
        <button>Comenzar a jugar
        <img className="img-play" src={play} alt="play"></img>
        </button>
      </div>
      <div className="TrackBoard-container">
        <TrackBoard />
      </div>
      <div className="Buttons-container-rolldice">
        <button>
          Lanzar dados
          <img className="img-dice" src={dice} alt="dice"></img>
        </button>
      </div>
    </div>
  );
}

export default App;
