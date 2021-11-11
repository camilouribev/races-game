package com.example.demo.infra.documents;

import com.example.demo.domain.game.Player;
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
    private Player secondPlace;
    private Player thirdPlace;

    public GameDocument(){
        this.players = new HashMap<>();
    }
    public String getId() {
        return id;
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

    public Player getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(Player secondPlace) {
        this.secondPlace = secondPlace;
    }

    public Player getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(Player thirdPlace) {
        this.thirdPlace = thirdPlace;
    }
}
