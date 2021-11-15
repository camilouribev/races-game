import React from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import HomePage from "./pages/HomePage";
import CarGame from "./pages/CarGame";
import AddPlayers from "./pages/AddPlayers";
import Podium from "./pages/Podium";
import "./App.css";
import { StoreProvider } from "./provider/Provider";

function App() {
  return (
    <StoreProvider>
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<HomePage />} />
          <Route exact path="/game" element={<CarGame />} />
          <Route exact path="/addPlayers" element={<AddPlayers />} />
          <Route exact path="/podium" element={<Podium />} />
        </Routes>
      </BrowserRouter>
    </StoreProvider>
  );
}

export default App;
