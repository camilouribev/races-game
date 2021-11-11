package com.example.demo.domain;

import java.io.Serializable;
import java.time.Instant;

public class CarMoved implements Serializable {
    private String gameId;
    private String playerId;
    private String type;
    private Integer distanceMoved;
    private Instant date;

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

    @Override
    public String toString() {
        return "CarMoved{" +
                "gameId='" + gameId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", type='" + type + '\'' +
                ", distanceMoved='" + distanceMoved + '\'' +
                ", date=" + date +
                '}';
    }
}
