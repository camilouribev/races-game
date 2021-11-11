import React from "react";
import meta from "../images/meta.png"

import "./TrackBoard.css";


export default function TrackBoard() {
  let board = [];
  let numberOfLanes =3;
  let trackLength=10;

  return <div className="Board-container">
    <div className="Carril">Carril 1 <div className="meta-container"> </div>  </div>
    <div className="Carril">Carril 2 <div className="meta-container">  </div></div>
    <div className="Carril">Carril 3 <div className="meta-container">   </div></div>
  </div>;
}
