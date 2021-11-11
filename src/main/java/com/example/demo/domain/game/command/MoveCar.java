package com.example.demo.domain.game.command;

import java.io.Serializable;

public class MoveCar implements Serializable {
    private String gameId;
    private String playerId;


    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
