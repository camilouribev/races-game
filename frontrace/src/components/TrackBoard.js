import React from "react";
import Car from "./Car";

import "./TrackBoard.css";


export default function TrackBoard() {
  let board = [];
  let numberOfLanes =3;
  let trackLength=10;

  return <div className="Board-container">
    <div className="Carril">Carril 1  <Car/> <div className="meta-container"></div> </div>
    <div className="Carril">Carril 2  <Car/>  <div className="meta-container"></div> </div>
    <div className="Carril">Carril 3  <Car/>  <div className="meta-container"></div> </div>
  </div>;
}
