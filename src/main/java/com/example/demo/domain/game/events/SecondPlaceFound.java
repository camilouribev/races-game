package com.example.demo.domain.game.events;

import java.io.Serializable;
import java.time.Instant;

public class SecondPlaceFound implements Serializable {
    private String name;
    private String id;
    private Instant date;
    private String type;
    private String gameId;
    private Integer carDrivenDistance;

    public SecondPlaceFound() {
        this.date = Instant.now();
        this.type = "game.secondplacefound";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getCarDrivenDistance() {
        return carDrivenDistance;
    }

    public void setCarDrivenDistance(Integer carDrivenDistance) {
        this.carDrivenDistance = carDrivenDistance;
    }

    @Override
    public String toString() {
        return "SecondPlaceFound{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", gameId='" + gameId + '\'' +
                ", carDrivenDistance=" + carDrivenDistance +
                '}';
    }
}
