package com.example.demo.infra.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
public class GameDocument {
    @Id
    private  String id;
    private  Map<String, PlayerDocument> players;
    private Boolean inProgress;
    private PlayerDocument winner;
    private PlayerDocument secondPlace;
    private PlayerDocument thirdPlace;
    private Integer trackLength;

    public GameDocument(){
        this.players = new HashMap<>();
    }
    public String getId() {
        return id;
    }

    public Integer getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(Integer trackLength) {
        this.trackLength = trackLength;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, PlayerDocument> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, PlayerDocument> players) {
        this.players = players;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public PlayerDocument getWinner() {
        return winner;
    }

    public void setWinner(PlayerDocument winner) {
        this.winner = winner;
    }

    public PlayerDocument getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(PlayerDocument secondPlace) {
        this.secondPlace = secondPlace;
    }

    public PlayerDocument getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(PlayerDocument thirdPlace) {
        this.thirdPlace = thirdPlace;
    }
}
