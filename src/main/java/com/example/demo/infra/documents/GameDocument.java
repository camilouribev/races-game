package com.example.demo.infra.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
public class GameDocument {
    @Id
    private  String id;
    private  Map<String, PlayerDocument> gamers;
    private Boolean inProgress;
    private PlayerDocument winner;

    public GameDocument(){
        this.gamers = new HashMap<>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, PlayerDocument> getPlayers() {
        return gamers;
    }

    public void setPlayers(Map<String, PlayerDocument> gamers) {
        this.gamers = gamers;
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

}
