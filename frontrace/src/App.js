import TrackBoard from "./components/TrackBoard";
import "./App.css";
import dice from "./images/dice.png";
import playerwoman from "./images/womanplayer.png";
import playerman from "./images/manplayer.png";
import play from "./images/play.png"

function App() {
  return (
    <div className="Title-container">
      <h1 className="Title"> CAR GAME RACE </h1>
      <div className="Buttons-container">
        <button>
          1. Agregar jugadores
          <img className="img-icon" src={playerwoman} alt="playerwoman"></img>
          <img className="img-icon" src={playerman} alt="playerman"></img>
        </button>
        <button> 
          2. Comenzar a jugar
        <img className="img-icon" src={play} alt="play"></img>
        </button>
        <button>
          3. Lanzar dados
          <img className="img-icon" src={dice} alt="dice"></img>
        </button>
      </div>
      <div className="TrackBoard-container">
        <TrackBoard />
      </div>
    </div>
  );
}

export default App;
