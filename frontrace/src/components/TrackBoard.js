import React from "react";
import Car1 from "./Car1";
import Car2 from "./Car2";
import Car3 from "./Car3";
import Car4 from "./Car4";

import "./TrackBoard.css";


export default function TrackBoard() {

  return <div className="Board-container">
    
    <div className="Carril">Carril 1  <Car1/> <div className="meta-container"></div> </div>
    <div className="Carril">Carril 2  <Car2/>  <div className="meta-container"></div> </div>
    <div className="Carril">Carril 3  <Car3/>  <div className="meta-container"></div> </div>
    <div className="Carril">Carril 4  <Car4/>  <div className="meta-container"></div> </div>

  </div>;
}
