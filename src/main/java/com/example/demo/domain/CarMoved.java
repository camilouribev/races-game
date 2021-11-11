package com.example.demo.domain;

import com.example.demo.domain.game.Player;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class CarMoved implements Serializable {
    private String gameId;
    private String playerId;
    private String type;
    private Integer distanceMoved;
    private Instant date;
    private Map<String, Player> players;

    public CarMoved() {
        this.type = "game.carmoved";
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

    public Integer getDistanceMoved() {
        return distanceMoved;
    }

    public void setDistanceMoved(Integer distanceMoved) {
        this.distanceMoved = distanceMoved;
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
        return "CarMoved{" +
                "gameId='" + gameId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", type='" + type + '\'' +
                ", distanceMoved=" + distanceMoved +
                ", date=" + date +
                ", players=" + players +
                '}';
    }
}
