package com.example.demo.domain.game.events;

import com.example.demo.domain.game.Player;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

public class GameFinished implements Serializable {
    private String gameId;
    private String playerId;
    private String type;
    private Instant date;
    private Map<String, Player> players;

    public GameFinished(){
        this.type = "game.gamefinished";
        this.date = Instant.now();

    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "GameFinished{" +
                "gameId='" + gameId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", players=" + players +
                '}';
    }
}
