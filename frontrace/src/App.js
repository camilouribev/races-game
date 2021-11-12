import React from "react";
import {
  BrowserRouter,
  Routes,
  Route,
  Link
} from "react-router-dom";
import HomePage from "./pages/HomePage";
import CarGame from "./pages/CarGame"
import AddPlayers from "./pages/AddPlayers"
import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<HomePage/>} />
        <Route exact path="/game" element={<CarGame/>} />
        <Route exact path="/addPlayers" element={<AddPlayers/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
