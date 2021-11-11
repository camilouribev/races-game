import React from "react";

import "./TrackBoard.css";

const verticalAxis = ["1","2","3","4","5","6","7","8"];
const horizontalAxis = ["A","B","C","D","E","F","G","H"];

export default function TrackBoard() {
  let board = [];
  let numberOfLanes =5;
  let trackLength=5;

  for (let i=0; i < horizontalAxis.length; i++) {
    for (let j=0; j < verticalAxis.length; i++) {
      board.push(<span>{horizontalAxis[i]} {verticalAxis[j]}</span>)
    }
  }


  return <div className="Board-container">Here is the track!</div>;
}
