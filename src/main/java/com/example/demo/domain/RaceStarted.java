package com.example.demo.domain;

import java.io.Serializable;
import java.time.Instant;

public class RaceStarted implements Serializable {
    private String name;
    private Instant date;
    private String type;
    private String gameId;
    private Integer trackLength;

    public RaceStarted() {
        this.date = Instant.now();
        this.type = "game.racestarted";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Integer getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(Integer trackLength) {
        this.trackLength = trackLength;
    }

    @Override
    public String toString() {
        return "RaceCreated{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", gameId='" + gameId + '\'' +
                ", trackLength=" + trackLength +
                '}';
    }
}
